package data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import noStrings.semicolon.data.models.Complexion;
import noStrings.semicolon.data.models.Gender;
import noStrings.semicolon.data.models.Profile;
import noStrings.semicolon.data.repositories.ProfileRepository;
import noStrings.semicolon.data.repositories.ProfileRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileRepositoryImplTest {

    private ProfileRepository profileRepository;

    @BeforeEach
    void setUp() {
        profileRepository = new ProfileRepositoryImpl();
    }


    private Profile buildSeeker(String name, Gender gender) {
        Profile profile = new Profile();
        profile.setName(name);
        profile.setGender(gender);
        profile.setDateOfBirth(LocalDate.of(1995, 6, 15));
        profile.setCurrentLocation("Lagos");
        profile.setHeightInCM(170);
        profile.setComplexion(Complexion.DARK);
        return profile;
    }


    @Test
    void testSave_assignsIdToNewSeeker() {
        Profile profile = buildSeeker("Ada", Gender.FEMALE);

        Profile saved = profileRepository.save(profile);

        assertNotNull(saved, "Saved Seeker should not be null");
        assertEquals(1, saved.getId(), "Saved Seeker should have an id greater than 0");
    }

    @Test
    void testSave_assignsUniqueIdsToMultipleSeekers() {
        Profile first  = profileRepository.save(buildSeeker("Ada",   Gender.FEMALE));
        Profile second = profileRepository.save(buildSeeker("Emeka", Gender.MALE));

        assertNotEquals(first.getId(), second.getId(),
                "Two different Seekers must not share the same id");
    }


    @Test
    void testSave_newSeeker_increasesCount() {
        assertEquals(0, profileRepository.count(), "Repository should start empty");

        profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        assertEquals(1, profileRepository.count(), "Count should be 1 after saving one Seeker");
    }


    @Test
    void testSave_existingSeeker_updatesStoredData() {
        // First, create the Seeker (id assigned by repository)
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        // Change some fields on the returned object
        saved.setName("Ada Updated");
        saved.setCurrentLocation("Abuja");

        // Pass the modified Seeker back to save() — it carries the id now
        Profile updated = profileRepository.save(saved);

        assertNotNull(updated, "save() should return the updated Seeker");
        assertEquals("Ada Updated", profileRepository.findById(saved.getId()).getName(),
                "Name should reflect the update");
        assertEquals("Abuja", profileRepository.findById(saved.getId()).getCurrentLocation(),
                "Location should reflect the update");
    }


    @Test
    void testSave_existingSeeker_doesNotIncreaseCount() {
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        saved.setName("Ada v2");
        profileRepository.save(saved); // update

        assertEquals(1, profileRepository.count(),
                "Count should remain 1 after updating via save()");
    }


    @Test
    void testSave_existingSeeker_oldDataIsNoLongerStored() {
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));
        int id = saved.getId();

        saved.setName("NewName");
        profileRepository.save(saved);

        assertEquals("NewName", profileRepository.findById(id).getName(),
                "findById() should return the updated name, not the old one");
    }

    @Test
    void testFindById_returnsSavedSeeker() {
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        Profile found = profileRepository.findById(saved.getId());

        assertNotNull(found, "Should find the Seeker that was saved");
        assertEquals(saved.getId(),   found.getId(),   "Id should match");
        assertEquals(saved.getName(), found.getName(), "Name should match");
    }


    @Test
    void testFindById_returnsNullForNonExistentId() {
        Profile found = profileRepository.findById(999);

        assertNull(found, "Should return null when no Seeker has the given id");
    }

    @Test
    void testFindAll_returnsEmptyListWhenNoSeekersExist() {
        List<Profile> all = profileRepository.findAll();

        assertNotNull(all,        "findAll() should never return null");
        assertTrue(all.isEmpty(), "List should be empty when no Seekers have been saved");
    }


    @Test
    void testFindAll_returnsAllSavedSeekers() {
        profileRepository.save(buildSeeker("Ada",   Gender.FEMALE));
        profileRepository.save(buildSeeker("Emeka", Gender.MALE));
        profileRepository.save(buildSeeker("Zara",  Gender.FEMALE));

        List<Profile> all = profileRepository.findAll();

        assertEquals(3, all.size(), "findAll() should return every saved Seeker");
    }


    @Test
    void testFindAll_returnsDefensiveCopy() {
        profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        List<Profile> all = profileRepository.findAll();
        all.clear(); // clear the returned list

        assertEquals(1, profileRepository.count(),
                "Clearing the returned list should NOT affect the repository");
    }


    @Test
    void testDeleteById_removesSeeker() {
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        profileRepository.deleteById(saved.getId());

        assertNull(profileRepository.findById(saved.getId()),
                "Deleted Seeker should no longer be retrievable");
    }


    @Test
    void testDeleteById_decreasesCount() {
        Profile saved = profileRepository.save(buildSeeker("Ada", Gender.FEMALE));
        profileRepository.save(buildSeeker("Emeka", Gender.MALE));

        profileRepository.deleteById(saved.getId());

        assertEquals(1, profileRepository.count(),
                "Count should drop to 1 after deleting one of two Seekers");
    }


    @Test
    void testDeleteById_doesNothingForNonExistentId() {
        profileRepository.save(buildSeeker("Ada", Gender.FEMALE));

        assertDoesNotThrow(() -> profileRepository.deleteById(999),
                "deleteById() should not throw when the id does not exist");

        assertEquals(1, profileRepository.count(),
                "Existing Seekers should be unaffected by a delete of a non-existent id");
    }


    @Test
    void testDeleteAll_removesAllSeekers() {
        profileRepository.save(buildSeeker("Ada", Gender.FEMALE));
        profileRepository.save(buildSeeker("Emeka", Gender.MALE));
        profileRepository.save(buildSeeker("Zara", Gender.FEMALE));

        profileRepository.deleteAll();

        assertEquals(0, profileRepository.count(),
                "Count should be 0 after deleteAll()");
        assertTrue(profileRepository.findAll().isEmpty(),
                "findAll() should return an empty list after deleteAll()");
    }


    @Test
    void testDeleteAll_onEmptyRepository_doesNotThrow() {
        assertDoesNotThrow(() -> profileRepository.deleteAll(),
                "deleteAll() should not throw when repository is already empty");
        assertEquals(0, profileRepository.count(),
                "Count should remain 0 after deleteAll() on empty repository");
    }


    @Test
    void testDeleteAll_thenSave_stillWorks() {
        profileRepository.save(buildSeeker("Ada", Gender.FEMALE));
        profileRepository.deleteAll();

        Profile savedAgain = profileRepository.save(buildSeeker("New Seeker", Gender.MALE));

        assertNotNull(savedAgain, "save() should still work after deleteAll()");
        assertTrue(savedAgain.getId() > 0, "Saved Seeker should still receive a valid id");
        assertEquals(1, profileRepository.count(),
                "Count should be 1 after saving one Seeker post-deleteAll()");
    }

    @Test
    void testCount_isZeroForEmptyRepository() {
        assertEquals(0, profileRepository.count(), "A new repository must have a count of 0");
    }


    @Test
    void testCount_reflectsNumberOfSavedSeekers() {
        profileRepository.save(buildSeeker("Ada",   Gender.FEMALE));
        profileRepository.save(buildSeeker("Emeka", Gender.MALE));
        profileRepository.save(buildSeeker("Zara",  Gender.FEMALE));

        assertEquals(3, profileRepository.count(), "Count must equal the number of saved Seekers");
    }
}
