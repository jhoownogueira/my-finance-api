package globals;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public class Persistence {

    @Inject
    public EntityManager persistence;

}


