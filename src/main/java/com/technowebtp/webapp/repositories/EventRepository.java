package com.technowebtp.webapp.repositories;


import com.technowebtp.webapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
