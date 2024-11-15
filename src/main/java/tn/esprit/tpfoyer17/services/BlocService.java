package tn.esprit.tpfoyer17.services;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer17.entities.Bloc;
import tn.esprit.tpfoyer17.entities.Chambre;
import tn.esprit.tpfoyer17.repositories.BlocRepository;
import tn.esprit.tpfoyer17.repositories.ChambreRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlocService implements IBlocService {

    final BlocRepository blocRepository;
    final ChambreRepository chambreRepository;

    @Override
    public Bloc addBloc(Bloc bloc) {
        log.info("Ajout d'un nouveau bloc : {}", bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return (List<Bloc>) blocRepository.findAll();
    }

    @Override
    public Bloc getBlocById(long idBloc) {
        return blocRepository.findById(idBloc)
                .orElseThrow(() -> {
                    log.warn("Bloc avec ID {} non trouvé", idBloc);
                    return new RuntimeException("Bloc avec ID " + idBloc + " non trouvé");
                });
    }

    @Override
    public void deleteBloc(long idBloc) {
        log.info("Suppression du bloc avec ID : {}", idBloc);
        if (blocRepository.existsById(idBloc)) {
            blocRepository.deleteById(idBloc);
        } else {
            log.warn("Bloc avec ID {} non trouvé pour la suppression", idBloc);
            throw new RuntimeException("Bloc avec ID " + idBloc + " non trouvé pour la suppression");
        }
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        log.info("Mise à jour du bloc : {}", bloc);
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc)
                .orElseThrow(() -> {
                    log.warn("Bloc avec ID {} non trouvé pour l'affectation de chambres", idBloc);
                    return new RuntimeException("Bloc avec ID " + idBloc + " non trouvé pour l'affectation de chambres");
                });

        List<Chambre> chambres = (List<Chambre>) chambreRepository.findAllById(numChambre);
        chambres.forEach(chambre -> chambre.setBloc(bloc));

        chambreRepository.saveAll(chambres); // Sauvegarder toutes les chambres après les modifications
        log.info("Affectation de chambres au bloc : {}", bloc);
        return bloc;
    }
}
