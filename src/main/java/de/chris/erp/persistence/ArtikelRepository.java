package de.chris.erp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/** Enthält die Persistenzlogik für {@link Artikel}
 * @param <A> {@link Artikel}
 * @param <L> Typ der Id von {@link Artikel}
 */
@Repository
public interface ArtikelRepository<A, L extends Number> extends JpaRepository<Artikel,Long>, ArtikelRepositoryCustom
{

}
