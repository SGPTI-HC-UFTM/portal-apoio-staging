package net.ebserh.hctm.config;

import jakarta.annotation.security.DeclareRoles;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import net.ebserh.hctm.util.Base64Sha256PasswordHash;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.jsf",
                errorPage = "/login.jsf?error=true")
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "portalApoioDS",
        callerQuery = "SELECT password FROM auth.usuarios WHERE login = ?",
        groupsQuery = """
            SELECT DISTINCT g.grupo
            FROM auth.usuarios u
            JOIN auth.usuarios_grupos ug ON u.id = ug.usuario_id
            JOIN auth.grupos g ON ug.grupo_id = g.id
            WHERE u.login = ?""",
        hashAlgorithm = Base64Sha256PasswordHash.class
)
@FacesConfig
@ApplicationScoped
@DeclareRoles("ADMIN")
public class ApplicationConfig {

}
