package semicolon.noStrings.data.repositories;

import semicolon.noStrings.data.models.Profile;
import java.util.ArrayList;
import java.util.List;

public class ProfileRepositoryImpl implements ProfileRepository {

    private List<Profile> profiles = new ArrayList<>();

    private int idCounter = 1;


    @Override
    public Profile save(Profile profile) {

        if (profile.getId() == 0) {
            profile.setId(idCounter++);
            profiles.add(profile);
            return profile;
        }

       for (int i = 0; i < profiles.size(); i++) {
            if (profiles.get(i).getId() == profile.getId()) {
                // Swap the old Seeker at this position with the updated one
                profiles.set(i, profile);
                return profile;
            }
        }

        profiles.add(profile);
        return profile;
    }

    @Override
    public Profile findById(int id) {
        for (Profile profile : profiles) {
            if (profile.getId() == id) {
                return profile;
            }
        }
        return null;
    }


    @Override
    public List<Profile> findAll() {
        return new ArrayList<>(profiles);
    }


    @Override
    public void deleteById(int id) {
        profiles.removeIf(seeker -> seeker.getId() == id);
    }


    @Override
    public void deleteAll() {
        profiles.clear();
    }

    @Override
    public int count() {
        return profiles.size();
    }

}
