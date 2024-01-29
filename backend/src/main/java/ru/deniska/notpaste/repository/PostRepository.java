package ru.deniska.notpaste.repository;


import jakarta.transaction.Transactional;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.deniska.notpaste.domain.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreationTimeDesc(Limit of);

    List<Post> findAllByOrderByCreationTimeDesc();

    @Query(value = "SELECT * FROM post WHERE title LIKE CONCAT('%', ?1, '%') ORDER BY update_time DESC", nativeQuery = true)
    List<Post> searchPostByTitle(String query);

    List<Post> findAllByUser_Id(long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM post WHERE update_time <= SUBDATE(CURRENT_DATE, deletion_interval)", nativeQuery = true)
    void deleteOutDated();
}
