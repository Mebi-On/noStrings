package semicolon.noStrings.data.repositories;

import semicolon.noStrings.data.models.Profile;

import java.util.List;

public interface ProfileRepository {

    Profile save(Profile profile);

    Profile findById(int id);

    List<Profile> findAll();

    void deleteById(int id);

    void deleteAll();

    int count();
}
