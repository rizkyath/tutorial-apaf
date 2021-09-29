package apap.tutorial.pergipergi.repository;


import apap.tutorial.pergipergi.model.DestinasiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinasiDb extends JpaRepository<DestinasiModel, Long> {
}
