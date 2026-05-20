package data.models;

import org.junit.jupiter.api.Test;
import semicolon.noStrings.data.models.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {

    private Profile createProfile() {
        Profile profile = new Profile();
        profile.setName("Ada");
        profile.setDateOfBirth(LocalDate.of(1995, 6, 15));
        profile.setGender(Gender.FEMALE);
        profile.setCurrentLocation("Lagos");
        profile.setHeightInCM(170);
        profile.setComplexion(Complexion.DARK);
        profile.setBodyType(BodyType.FIT);
        profile.setPrefrence(Prefrence.MALE);
        return profile;
    }


    @Test
    void testSetAndGetName() {
        Profile profile = new Profile();
        profile.setName("Ada");

        assertEquals("Ada", profile.getName());
    }

    @Test
    void testSetAndGetDateOfBirth() {
        Profile profile = new Profile();
        LocalDate dob = LocalDate.of(2000, 1, 1);

        profile.setDateOfBirth(dob);

        assertEquals(dob, profile.getDateOfBirth());
    }

    @Test
    void testSetAndGetGender() {
        Profile profile = new Profile();

        profile.setGender(Gender.MALE);

        assertEquals(Gender.MALE, profile.getGender());
    }

    @Test
    void testSetAndGetLocation() {
        Profile profile = new Profile();

        profile.setCurrentLocation("Abuja");

        assertEquals("Abuja", profile.getCurrentLocation());
    }

    @Test
    void testSetAndGetHeight() {
        Profile profile = new Profile();

        profile.setHeightInCM(180);

        assertEquals(180, profile.getHeightInCM());
    }

    @Test
    void testSetAndGetComplexion() {
        Profile profile = new Profile();

        profile.setComplexion(Complexion.LIGHT);

        assertEquals(Complexion.LIGHT, profile.getComplexion());
    }

    @Test
    void testSetAndGetBodyType() {
        Profile profile = new Profile();

        profile.setBodyType(BodyType.SLIM);

        assertEquals(BodyType.SLIM, profile.getBodyType());
    }

    @Test
    void testSetAndGetPreference() {
        Profile profile = new Profile();

        profile.setPrefrence(Prefrence.FEMALE);

        assertEquals(Prefrence.FEMALE, profile.getPrefrence());
    }


    @Test
    void testFullProfileDataIntegrity() {
        Profile profile = createProfile();

        assertAll(
                () -> assertEquals("Ada", profile.getName()),
                () -> assertEquals(Gender.FEMALE, profile.getGender()),
                () -> assertEquals("Lagos", profile.getCurrentLocation()),
                () -> assertEquals(170, profile.getHeightInCM()),
                () -> assertEquals(Complexion.DARK, profile.getComplexion()),
                () -> assertEquals(BodyType.FIT, profile.getBodyType()),
                () -> assertEquals(Prefrence.MALE, profile.getPrefrence())
        );
    }


    @Test
    void testDefaultProfileValuesAreNullOrZero() {
        Profile profile = new Profile();

        assertNull(profile.getName());
        assertNull(profile.getGender());
        assertNull(profile.getDateOfBirth());
        assertNull(profile.getCurrentLocation());
        assertNull(profile.getComplexion());
        assertNull(profile.getBodyType());
        assertNull(profile.getPrefrence());
        assertEquals(0, profile.getHeightInCM());
    }


    @Test
    void testProfileCanBeUpdated() {
        Profile profile = createProfile();

        profile.setName("Updated Name");
        profile.setCurrentLocation("Abuja");
        profile.setHeightInCM(190);

        assertEquals("Updated Name", profile.getName());
        assertEquals("Abuja", profile.getCurrentLocation());
        assertEquals(190, profile.getHeightInCM());
    }
}