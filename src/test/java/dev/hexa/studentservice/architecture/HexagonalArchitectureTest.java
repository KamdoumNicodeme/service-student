package dev.hexa.studentservice.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import dev.hexa.studentservice.application.annotation.*;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "dev.hexa.studentservice", importOptions = {ImportOption.DoNotIncludeTests.class})
public class HexagonalArchitectureTest {

    @ArchTest
    static final ArchRule hexagonal_architecture = layeredArchitecture()
            .consideringAllDependencies()
            .withOptionalLayers(true)
            .layer("domain")
            .definedBy("..domain..")
            .layer("application")
            .definedBy("..application..")
            .layer("adapters")
            .definedBy("..infrastructure.adapters..")
            .layer("primary_adapters")
            .definedBy("..infrastructure.adapters.primary..")
            .layer("secondary_adapters")
            .definedBy("..infrastructure.adapters.secondary..")
            .whereLayer("adapters")
            .mayNotBeAccessedByAnyLayer()
            .whereLayer("primary_adapters")
            .mayOnlyBeAccessedByLayers("application", "domain")
            .whereLayer("secondary_adapters")
            .mayOnlyBeAccessedByLayers("application", "domain");

    @ArchTest
    static final ArchRule domain_only_depends_on_itself = classes()
            .that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "..domain..",
                    "java..",
                    "lombok..")
            .because("Domain layer should only depend on itself and standard libraries");

    @ArchTest
    static final ArchRule primary_ports_must_reside_in_application_layer = classes()
            .that().areAnnotatedWith(PrimaryPort.class)
            .should().resideInAPackage("..application.ports.primary..")
            .because("Primary ports must reside in application ports package");

    @ArchTest
    static final ArchRule secondary_ports_must_reside_in_application_layer = classes()
            .that().areAnnotatedWith(SecondaryPort.class)
            .should().resideInAPackage("..application.ports.secondary..")
            .because("Secondary ports must reside in application ports package");

    @ArchTest
    static final ArchRule primary_ports_must_be_interfaces = classes()
            .that().areAnnotatedWith(PrimaryPort.class)
            .should().beInterfaces()
            .because("Primary ports must be interfaces");

    @ArchTest
    static final ArchRule secondary_ports_must_be_interfaces = classes()
            .that().areAnnotatedWith(SecondaryPort.class)
            .should().beInterfaces()
            .because("Secondary ports must be interfaces");

    @ArchTest
    static final ArchRule ports_must_be_annotated = classes()
            .that().resideInAnyPackage("..application.ports..")
            .and().haveNameMatching(".*Port.*")
            .should().beAnnotatedWith(PrimaryPort.class)
            .orShould().beAnnotatedWith(SecondaryPort.class)
            .because("Ports must be annotated with @PrimaryPort or @SecondaryPort");

    @ArchTest
    static final ArchRule primary_adapters_must_depend_on_primary_ports = classes()
            .that().areAnnotatedWith(PrimaryAdapter.class)
            .should().onlyDependOnClassesThat()
            .areAnnotatedWith(PrimaryPort.class)
            .orShould().resideInAnyPackage(
                    "..application.ports.primary..",
                    "..infrastructure.adapters..")
            .because("Primary adapters must depend on primary ports or allowed infrastructure components");


    @ArchTest
    static final ArchRule primary_adapters_must_reside_in_primary_infrastructure = classes()
            .that().areAnnotatedWith(PrimaryAdapter.class)
            .should().resideInAPackage("..infrastructure.adapters.primary..")
            .because("Primary adapters must reside in primary infrastructure package");

    @ArchTest
    static final ArchRule secondary_adapters_must_reside_in_secondary_infrastructure = classes()
            .that().areAnnotatedWith(SecondaryAdapter.class)
            .should().resideInAPackage("..infrastructure.adapters.secondary..")
            .because("Secondary adapters must reside in secondary infrastructure package");

    @ArchTest
    static final ArchRule adapters_must_be_annotated = classes()
            .that().resideInAnyPackage("..infrastructure.adapters..")
            .and().haveNameMatching(".*Adapter.*")
            .should().beAnnotatedWith(PrimaryAdapter.class)
            .orShould().beAnnotatedWith(SecondaryAdapter.class)
            .because("Adapters must be annotated with @PrimaryAdapter or @SecondaryAdapter");
}
