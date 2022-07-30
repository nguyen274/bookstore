package com.project.library;

import com.project.library.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest
class LibraryManagementProjectApplicationTests {

	@Test
	public void whenPathExpressionIsUsedForSingleValuedAssociation_thenCreatesImplicitInnerJoin() {
		EntityManager entityManager = null;
		TypedQuery<User> query
				= entityManager.createQuery(
				"SELECT s.staffName, s.staffCode, s.gender, s.phoneNumber, s.dateOfBirth FROM User u inner join Staff s on s.users = u.staff", User.class);
		List<User> resultList = query.getResultList();

		System.out.println(resultList);
	}

}
