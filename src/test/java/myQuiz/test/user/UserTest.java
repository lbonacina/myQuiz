package myQuiz.test.user;

import myQuiz.repository.RoleRepository;
import myQuiz.repository.UserConstraintException;
import myQuiz.repository.UserRepository;
import myQuiz.service.UserService;
import myQuiz.util.ResourcesProducer;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

/**
 * User: eluibon
 * Date: 06/03/13
 * Time: 11.51
 */
@RunWith(Arquillian.class)
public class UserTest {

    @Deployment
    public static Archive<?> createDeployment() {

        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage("myQuiz.model.user")
                .addClasses(UserService.class, UserRepository.class, RoleRepository.class, UserConstraintException.class)
                .addClasses(ResourcesProducer.class)
                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsResource("import.sql", "import.sql")
                .addAsWebInfResource(new File("src/test/resources/myQuiz-ds.xml"))
                .addAsLibraries(resolver.artifact("org.apache.shiro:shiro-core").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.apache.shiro:shiro-web").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.mysema.querydsl:querydsl-jpa").resolveAsFiles())
                .addAsLibraries(resolver.artifact("com.mysema.querydsl:querydsl-apt").resolveAsFiles())
                .addAsLibraries(resolver.artifact("org.springframework.data:spring-data-jpa").resolveAsFiles())
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject UserService userService;


    @Test
    public void findAllWithPermissionsiFilter() {

        // TODO : integrate Shiro testing by setting the Subject with permissions and verify that the findAll method of userService correctly filter the users
    }
}
