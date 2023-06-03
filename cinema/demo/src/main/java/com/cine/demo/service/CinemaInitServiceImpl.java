package com.cine.demo.service;

import com.cine.demo.dao.*;
import com.cine.demo.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void initVilles() {
        Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(nameVille->{
            Ville ville=new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });

    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v->{
            Stream.of("Megarama","IMAX","Founoun","Rif")
                    .forEach(nameCinema->{
                        Cinema cinema=new Cinema();
                        cinema.setName(nameCinema);
                        cinema.setNbSalle(3+(int)(Math.random()*7));
                        cinema.setVille(v);
                        cinemaRepository.save(cinema);
                    });
        });

    }

    @Override
    public void initSalles() {
       cinemaRepository.findAll().forEach(c->{
           for(int i=0;i<c.getNbSalle();i++){
               Salle salle=new Salle();
               salle.setName("Salle"+(i+1));
               salle.setCinema(c);
               salle.setNombrePlace(10+(int)(Math.random()*10));
               salleRepository.save(salle);
           }
       });
    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle-> {
            for(int i=0;i<salle.getNombrePlace();i++){
                Place place=new Place();
                place.setNum(i+1);
                place.setSalle(salle);
                placeRepository.save(place);
            }

        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat=new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(s->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }



    @Override
    public void initCategories() {
        Stream.of("Histoire","Action","Fiction","Drame").forEach(cat->{
            Categorie categorie=new Categorie();
            categorie.setName(cat);
            categoryRepository.save(categorie);
                });
    }

    @Override
    public void initFilms() {
        double[] duree=new double[]{1,1.5,2,2.5,3};
        List<Categorie> categories=categoryRepository.findAll();
        Stream.of("Harry Potter","LOTR","Spiderman","The matrix").forEach(f->{
            Film film=new Film();
            film.setTitre(f);
            film.setDuree(duree[new Random().nextInt(duree.length)]);
            film.setPhoto(f.replaceAll(" ","")+".jpg");
            film.setCategorie((categories.get(new Random().nextInt(categories.size()))));
            filmRepository.save(film);
        });
    }
    @Override
    public void initProjections() {
        double[] prices=new double[]{30,50,60,70,90,100};
        List<Film> films = filmRepository.findAll();
        villeRepository.findAll().forEach(v->{
            v.getCinemas().forEach(c->{
                c.getSalles().forEach(s->{
                    int index = new Random().nextInt(films.size());
                    Film f = films.get(index);

                        seanceRepository.findAll().forEach(se->{
                            Projection projection=new Projection();
                            projection.setDateProj((new Date()));
                            projection.setFilm(f);
                            projection.setPrix(prices[new Random().nextInt(prices.length)]);
                            projection.setSalle(s);
                            projection.setSeance(se);
                            projectionRepository.save(projection);
                        });


                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p->{
            p.getSalle().getPlaces().forEach(place->{
                Ticket ticket=new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(p.getPrix());
                ticket.setReserve(false);
                ticketRepository.save(ticket);
                    });
        });

    }
}
