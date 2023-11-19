package design.car;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import design.car.domain.Owner;
import design.car.domain.OwnerRepository;

@DataJpaTest
class OwnerRepositoryTest {
	@Autowired
	private OwnerRepository repository;
	
	@Test
	void saveOwner() {
		repository.save(new Owner("UniqueFirstName", "LastName"));
		assertThat(repository.findByFirstname("UniqueFirstName").isPresent()).isTrue();
	}

	@Test
	void deleteOwners() {
		repository.save(new Owner("Alice", "Liddell"));
		repository.deleteAll();
		assertThat(repository.count()).isEqualTo(0);
	}
}
