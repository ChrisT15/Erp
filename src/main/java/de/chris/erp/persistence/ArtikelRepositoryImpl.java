package de.chris.erp.persistence;

import de.chris.erp.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enthält Implementierung von selbst erstellten Methode mit Persistenzlogik für {@link Artikel}
 */
@Repository
public class ArtikelRepositoryImpl implements ArtikelRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Artikel> sucheArtikelNachEigenschaften(ArtikelSuchFormular artikelSuchFormular)
    {
        String abfrage = "from Artikel a ";
        Map<String,Object> eigenschaften = new HashMap<>();

        if(!StringUtil.isEmptyOrNull(artikelSuchFormular.getNummer()))
        {
            eigenschaften.put("nummer",artikelSuchFormular.getNummer());
        }
        if(null != artikelSuchFormular.getBezeichnung() && !artikelSuchFormular.getBezeichnung().isEmpty())
        {
            eigenschaften.put("bezeichnung",String.format("'%s'",artikelSuchFormular.getBezeichnung()));
        }

        if(!eigenschaften.isEmpty())
        {
            abfrage += " where ";

             abfrage += eigenschaften.entrySet()
                    .stream()
                    .map(entry -> "a." + entry.getKey() + " = " + entry.getValue())
                    .collect(Collectors.joining(" and "));
        }

        return entityManager.createQuery(abfrage).getResultList();
    }
}
