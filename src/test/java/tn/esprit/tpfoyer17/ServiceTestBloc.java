package tn.esprit.tpfoyer17;
import tn.esprit.tpfoyer17.entities.Bloc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.repositories.BlocRepository;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
public class ServiceTestBloc {

    @Autowired
    private BlocRepository blocRepository;

    private Bloc bloc;

    @BeforeEach
    void setUp() {
        bloc = new Bloc();
        Bloc.setName("Bloc Test");
        bloc.setDescription("Description du Bloc Test");
    }

    @Test
    void testAddBloc() {
        // Ajouter un bloc dans le repository
        log.info("Ajout d'un bloc : {}", bloc);
        Bloc savedBloc = blocRepository.save(bloc);

        // Vérifications
        assertNotNull(savedBloc.getId(), "L'ID du bloc ne doit pas être nul après sauvegarde.");
        assertEquals("Bloc Test", savedBloc.getName(), "Le nom du bloc doit correspondre.");
        assertEquals("Description du Bloc Test", savedBloc.getDescription(), "La description doit correspondre.");
    }

    @Test
    void testReadBloc() {
        // Sauvegarder un bloc et le lire
        Bloc savedBloc = blocRepository.save(bloc);
        Bloc foundBloc = blocRepository.findById(savedBloc.getId()).orElse(null);

        // Vérifications
        assertNotNull(foundBloc, "Le bloc trouvé ne doit pas être nul.");
        assertEquals(savedBloc.getId(), foundBloc.getId(), "L'ID du bloc doit correspondre.");
        assertEquals("Bloc Test", foundBloc.getName(), "Le nom du bloc doit correspondre.");
    }

    @Test
    void testUpdateBloc() {
        // Sauvegarder et mettre à jour un bloc
        Bloc savedBloc = blocRepository.save(bloc);
        savedBloc.setName("Updated Bloc");
        savedBloc.setDescription("Updated Description");

        Bloc updatedBloc = blocRepository.save(savedBloc);

        // Vérifications
        assertEquals("Updated Bloc", updatedBloc.getName(), "Le nom mis à jour doit correspond");
    }
}
