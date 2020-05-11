package de.chris.erp.persistence;

import java.util.List;

public interface ArtikelRepositoryCustom
{
    public List<Artikel> sucheArtikelNachEigenschaften(Artikel artikel);

}
