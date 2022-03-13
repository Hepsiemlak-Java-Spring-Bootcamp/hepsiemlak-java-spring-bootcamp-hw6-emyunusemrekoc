package main.java.emlakburada.repository;

import emlakburada.model.Advert;
import emlakburada.model.AdvertPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertPhotoRepository extends JpaRepository<AdvertPhoto, Integer> {
}
