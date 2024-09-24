package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final PublisherRepository publisherRepository;


    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");
        Author ericSaved = authorRepository.save(eric);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Author rodSaved = authorRepository.save(rod);

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("123456");

        Book dddSaved = bookRepository.save(ddd);

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");
        Book noEJBSaved = bookRepository.save(noEJB);


        Publisher guru = new Publisher();
        guru.setPublisherName("Guru");
        guru.setAddress("Lizarre");
        guru.setCity("Bilbo");
        guru.setState("BC");
        guru.setZip(48000);

        Publisher guruSaved = publisherRepository.save(guru);

        eric.getBooks().add(dddSaved);
        rod.getBooks().add(noEJBSaved);
        noEJB.getAuthors().add(rodSaved);
        ddd.getAuthors().add(ericSaved);
        guru.getBooks().add(dddSaved);
        guru.getBooks().add(noEJBSaved);
        ddd.setPublisher(guruSaved);
        noEJB.setPublisher(guruSaved);

        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        bookRepository.save(dddSaved);
        bookRepository.save(noEJBSaved);
        publisherRepository.save(guruSaved);
//        dddSaved.setPublisher(guruSaved);
//        noEJBSaved.setPublisher(guruSaved);
//
//        bookRepository.save(dddSaved);
//        bookRepository.save(noEJBSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());





    }
}
