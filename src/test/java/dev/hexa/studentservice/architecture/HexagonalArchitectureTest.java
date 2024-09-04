package dev.hexa.studentservice.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import dev.hexa.studentservice.application.annotation.*;
import dev.hexa.studentservice.application.service.StudentService;

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
            .layer("adapter")
            .definedBy("..adapter..")
            .layer("infrastructure")
            .definedBy("..infrastructure..")
            .whereLayer("infrastructure")
            .mayNotBeAccessedByAnyLayer()
            .whereLayer("adapter")
            .mayOnlyBeAccessedByLayers("domain");

    @ArchTest
    static final ArchRule domain_only_depends_on_itself = classes()
            .that().resideInAPackage("..domain..")
            .should().onlyDependOnClassesThat().resideInAnyPackage(
                    "..domain..",
                    "..studentservice.architecture..",
                    "..studentservice.adapter..",
                    "..studentservice.infrastructure..",
                    "..studentservice.infrastructure.adapters.secondary.persitance.entity..",
                    "..studentservice.infrastructure.adapters.secondary.persitance.mapper..",
                    "..studentservice.infrastructure.adapters.secondary.persitance.repository..",
                    "..studentservice.application..",
                    "..studentservice.application.ports.primary..",
                    "..studentservice.application.ports.secondary..",
                    "java..",
                    "lombok..")
            .because("Domain only depends on itself");


    @ArchTest
    static final ArchRule primary_ports_must_reside_in_domain_model = classes()
            .that().areAnnotatedWith(PrimaryPort.class)
            .should().resideInAPackage("..application..")
            .because("Primary ports must reside in application")
            .allowEmptyShould(true);

    @ArchTest
    static final ArchRule secondary_ports_must_reside_in_domain_model = classes()
            .that().areAnnotatedWith(SecondaryPort.class)
            .should().resideInAPackage("..application..")
            .because("Secondary ports must reside in application")
            .allowEmptyShould(true);


    @ArchTest
    static final ArchRule primary_ports_must_be_interfaces = classes()
            .that().areAnnotatedWith(PrimaryPort.class)
            .should().beInterfaces()
            .because("Primary ports must be interfaces")
            .allowEmptyShould(true);


    @ArchTest
    static final ArchRule secondary_ports_must_be_interfaces = classes()
            .that().areAnnotatedWith(SecondaryPort.class)
            .should().beInterfaces()
            .because("Secondary ports must be interfaces")
            .allowEmptyShould(true);

    @ArchTest
    static final ArchRule ports_must_be_annotated = classes()
            .that().resideInAnyPackage("..domain..")
            .and().haveNameMatching(".*Port.*")
            .should().beAnnotatedWith(PrimaryPort.class)
            .orShould().beAnnotatedWith(SecondaryPort.class)
            .because("Ports must be annotated with @PrimaryPort or @SecondaryPort")
            .allowEmptyShould(true);


    @ArchTest
    static final ArchRule primary_adapters_depends_on_primary_ports = classes()
            .that().areAnnotatedWith(PrimaryPort.class)
            .should().onlyHaveDependentClassesThat().areAnnotatedWith(PrimaryAdapter.class)
            .orShould().resideInAPackage("..application..")
            .because("Primary adapters depends on primary ports")
            .allowEmptyShould(true);



    @ArchTest
    static final ArchRule services_should_only_depend_on_ports = classes()
            .that().areAnnotatedWith(Service.class)
            .should().onlyHaveDependentClassesThat().areAnnotatedWith(PrimaryPort.class)
            .orShould().onlyHaveDependentClassesThat().areAnnotatedWith(SecondaryPort.class)
            .because("Services should only depend on primary and secondary ports");


    @ArchTest
    static final ArchRule primary_adapters_must_reside_in_primary_infrastructure = classes()
            .that().areAnnotatedWith(PrimaryAdapter.class)
            .should().resideInAPackage("..infrastructure.adapters..")
            .because("Primary adapters must reside in primary infrastructure")
            .allowEmptyShould(true);


    @ArchTest
    static final ArchRule secondary_adapters_must_reside_in_secondary_infrastructure = classes()
            .that().areAnnotatedWith(SecondaryAdapter.class)
            .should().resideInAPackage("..infrastructure.adapters.secondary..")
            .because("Secondary adapters must reside in secondary infrastructure")
            .allowEmptyShould(true);

    @ArchTest
    static final ArchRule adapters_must_be_annotated = classes()
            .that().resideInAnyPackage("..infrastructure..")
            .and().haveNameMatching(".*Adapter.*")
            .should().beAnnotatedWith(PrimaryAdapter.class)
            .orShould().beAnnotatedWith(SecondaryAdapter.class)
            .because("Adapters must be annotated with @PrimaryAdapter or @SecondaryAdapter")
            .allowEmptyShould(true);

}
