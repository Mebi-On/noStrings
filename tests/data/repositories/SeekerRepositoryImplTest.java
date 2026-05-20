package data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import semicolon.noStrings.data.models.Seeker;
import semicolon.noStrings.data.repositories.SeekerRepository;
import semicolon.noStrings.data.repositories.SeekerRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeekerRepositoryImplTest {

    private SeekerRepository seekerRepository;

    @BeforeEach
    void setUp() {
        seekerRepository = new SeekerRepositoryImpl();
    }


    private Seeker buildSeeker() {
        return new Seeker();
    }

    @Test
    void testSave_assignsIdToNewSeeker() {
        Seeker saved = seekerRepository.save(buildSeeker());

        assertNotNull(saved);
        assertTrue(saved.getId() > 0, "Saved Seeker should have a generated id");
    }

    @Test
    void testSave_assignsUniqueIds() {
        Seeker first = seekerRepository.save(buildSeeker());
        Seeker second = seekerRepository.save(buildSeeker());

        assertNotEquals(first.getId(), second.getId());
    }

    @Test
    void testSave_increasesCount() {
        assertEquals(0, seekerRepository.count());

        seekerRepository.save(buildSeeker());

        assertEquals(1, seekerRepository.count());
    }


    @Test
    void testSave_existingSeeker_doesNotIncreaseCount() {
        Seeker saved = seekerRepository.save(buildSeeker());

        seekerRepository.save(saved);

        assertEquals(1, seekerRepository.count());
    }

    @Test
    void testSave_existingSeeker_keepsSameId() {
        Seeker saved = seekerRepository.save(buildSeeker());
        int id = saved.getId();

        seekerRepository.save(saved);

        assertEquals(id, saved.getId());
    }


    @Test
    void testFindById_returnsSavedSeeker() {
        Seeker saved = seekerRepository.save(buildSeeker());

        Seeker found = seekerRepository.findById(saved.getId());

        assertNotNull(found);
        assertEquals(saved.getId(), found.getId());
    }

    @Test
    void testFindById_returnsNullForNonExistent() {
        assertNull(seekerRepository.findById(999));
    }


    @Test
    void testFindAll_returnsEmptyList() {
        List<Seeker> all = seekerRepository.findAll();

        assertNotNull(all);
        assertTrue(all.isEmpty());
    }

    @Test
    void testFindAll_returnsAllSeekers() {
        seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());

        List<Seeker> all = seekerRepository.findAll();

        assertEquals(3, all.size());
    }

    @Test
    void testFindAll_returnsDefensiveCopy() {
        seekerRepository.save(buildSeeker());

        List<Seeker> all = seekerRepository.findAll();
        all.clear();

        assertEquals(1, seekerRepository.count());
    }


    @Test
    void testDeleteById_removesSeeker() {
        Seeker saved = seekerRepository.save(buildSeeker());

        seekerRepository.deleteById(saved.getId());

        assertNull(seekerRepository.findById(saved.getId()));
    }

    @Test
    void testDeleteById_decreasesCount() {
        Seeker first = seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());

        seekerRepository.deleteById(first.getId());

        assertEquals(1, seekerRepository.count());
    }

    @Test
    void testDeleteById_nonExistentDoesNothing() {
        seekerRepository.save(buildSeeker());

        assertDoesNotThrow(() -> seekerRepository.deleteById(999));

        assertEquals(1, seekerRepository.count());
    }


    @Test
    void testDeleteAll_removesAll() {
        seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());

        seekerRepository.deleteAll();

        assertEquals(0, seekerRepository.count());
        assertTrue(seekerRepository.findAll().isEmpty());
    }

    @Test
    void testDeleteAll_emptyRepository_safe() {
        assertDoesNotThrow(() -> seekerRepository.deleteAll());
        assertEquals(0, seekerRepository.count());
    }

    @Test
    void testDeleteAll_thenSaveWorks() {
        seekerRepository.save(buildSeeker());
        seekerRepository.deleteAll();

        Seeker saved = seekerRepository.save(buildSeeker());

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals(1, seekerRepository.count());
    }


    @Test
    void testCount_emptyRepository() {
        assertEquals(0, seekerRepository.count());
    }

    @Test
    void testCount_reflectsSavedSeekers() {
        seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());
        seekerRepository.save(buildSeeker());

        assertEquals(3, seekerRepository.count());
    }
}