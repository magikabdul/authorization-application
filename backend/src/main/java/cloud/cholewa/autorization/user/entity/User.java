package cloud.cholewa.autorization.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String username;

    private String password;

    private boolean credentialsNonExpired;
    private boolean accountNonExpired;
    private boolean accountNonLocked;

    private boolean enabled;

    private String roles;

    @OneToOne
    private AccessToken accessToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> set = new HashSet<>();

        StringBuilder allRoles = new StringBuilder(roles.trim());

        while (allRoles.length() > 0) {
            int position = allRoles.indexOf(",");

            String r;
            if (position > -1) {
                r = allRoles.substring(0, position).trim().toUpperCase();
                allRoles.delete(0, position + 1);
            } else {
                r = allRoles.substring(0).toUpperCase();
                allRoles.setLength(0);
            }

            set.add(new SimpleGrantedAuthority("ROLE_" + r));
        }

        return set;
    }
}
