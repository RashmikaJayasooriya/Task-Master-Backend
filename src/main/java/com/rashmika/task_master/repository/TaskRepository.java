package com.rashmika.task_master.repository;

import com.rashmika.task_master.entity.Task;
import com.rashmika.task_master.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByIdAndUser(long l, User user);

    List<Task> findAllByUser(User user);

    List<Task> findAllByCompletedEqualsAndUser(boolean b, User user);
}
