package org.jhipster.intro;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.jhipster.intro");

        noClasses()
            .that()
            .resideInAnyPackage("org.jhipster.intro.service..")
            .or()
            .resideInAnyPackage("org.jhipster.intro.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.jhipster.intro.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
