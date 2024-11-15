package tn.esprit.tpfoyer17;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Foyer;
import tn.esprit.tpfoyer17.repositories.FoyerRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoyerRepositoryTest {

    @Autowired
    private FoyerRepository foyerRepository;

    private Foyer foyer;

    @BeforeEach
    void setUp() {
        foyer = new Foyer();
        foyer.setNomFoyer("Foyer Test");
        foyer.setCapaciteFoyer(200L);
    }

    @Test
    void testCreateFoyer() {
        Foyer savedFoyer = foyerRepository.save(foyer);
        assertNotNull(savedFoyer.getIdFoyer());
        assertEquals("Foyer Test", savedFoyer.getNomFoyer());
        assertEquals(200L, savedFoyer.getCapaciteFoyer());
    }

    @Test
    void testReadFoyer() {
        Foyer savedFoyer = foyerRepository.save(foyer);
        Foyer foundFoyer = foyerRepository.findById(savedFoyer.getIdFoyer()).orElse(null);

        assertNotNull(foundFoyer);
        assertEquals(savedFoyer.getIdFoyer(), foundFoyer.getIdFoyer());
        assertEquals("Foyer Test", foundFoyer.getNomFoyer());
    }

    @Test
    void testUpdateFoyer() {
        Foyer savedFoyer = foyerRepository.save(foyer);
        savedFoyer.setNomFoyer("Updated Foyer");
        savedFoyer.setCapaciteFoyer(250L);

        Foyer updatedFoyer = foyerRepository.save(savedFoyer);

        assertEquals("Updated Foyer", updatedFoyer.getNomFoyer());
        assertEquals(250L, updatedFoyer.getCapaciteFoyer());
    }

    @Test
    void testDeleteFoyer() {
        Foyer savedFoyer = foyerRepository.save(foyer);
        Long foyerId = savedFoyer.getIdFoyer();

        foyerRepository.deleteById(foyerId);

        Foyer deletedFoyer = foyerRepository.findById(foyerId).orElse(null);

        assertNull(deletedFoyer);
    }
}
