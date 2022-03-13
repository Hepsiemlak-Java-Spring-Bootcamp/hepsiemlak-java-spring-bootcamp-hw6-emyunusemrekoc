package main.java.emlakburada.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import emlakburada.model.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner,Integer> {
	
    Banner findById(int id);
}
