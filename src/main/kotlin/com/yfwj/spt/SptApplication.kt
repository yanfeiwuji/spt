package com.yfwj.spt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.domain.PageRequest
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.Id


@SpringBootApplication
class SptApplication

fun main(args: Array<String>) {
    runApplication<SptApplication>(*args)
}


@Entity
class Person {
    @Id
    lateinit var id: String
    lateinit var name: String
}

interface Re : PagingAndSortingRepository<Person, String>, QuerydslPredicateExecutor<Person>

@RequestMapping("p")
@RestController
class Pr() {
    @Autowired
    lateinit var re: Re;


    @PreAuthorize("hasRole('USER')")
    @GetMapping
    fun ass(pageRequest: PageRequest) {

        val p = QPerson.person.name.startsWith("123")
        re.findAll(p, pageRequest)
        val person: Person = Person()
    }
}
