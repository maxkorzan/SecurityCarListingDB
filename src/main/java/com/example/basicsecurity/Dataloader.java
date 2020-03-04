package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.activation.CommandObject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Dataloader implements CommandLineRunner {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CarRepository carRepository;

    @Override
    public void run(String... strings) throws Exception {

        //create "category"
        Category category = new Category();
        category.setName("Truck");

        //create "car"
        Car car = new Car();
        car.setName("Ford F-150");
        car.setYear("2020");
        car.setPrice("$35,499");
        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/f150_ur1kc5.jpg");

        //create empty set "cars", add "car", and add the set to "category"
        Set<Car> cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        //define and save category for "car"
        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("SUV");

        Car car1 = new Car();
        car1.setName("Land Rover Discovery");
        car1.setYear("2018");
        car1.setPrice("58,399");
        car1.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/landrover_ehtzmc.jpg");

        Car car2 = new Car();
        car2.setName("Porsche Cayenne Turbo S");
        car2.setYear("2019");
        car2.setPrice("$94,899");
        car2.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/cayenne_lerhuo.jpg");

        Car car3 = new Car();
        car3.setName("Mercedes-Benz GLS63 AMG");
        car3.setYear("2019");
        car3.setPrice("$78,299");
        car3.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/gls_amg_oy9ful.jpg");

        cars = new HashSet<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);

        category.setCars(cars);
        categoryRepository.save(category);

        car1.setCategory(category);
        car2.setCategory(category);
        car3.setCategory(category);
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);

//////////////////////////////////////////////////////////////////////


        category = new Category();
        category.setName("Commercial Fleet");
        car = new Car();
        car.setName("Chevrolet Silverado Z71");
        car.setYear("2019");
        car.setPrice("$47,899");
        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/silverado_ehheig.jpg");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("Car");
        car = new Car();
        car.setName("BMW 335i");
        car.setYear("2020");
        car.setPrice("$37,999");
        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924914/Images/vehicle/m3_vmeqbn.jpg");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////

        category = new Category();
        category.setName("Motorcycle");
        car = new Car();
        car.setName("Husqvarna 901 Adventure");
        car.setYear("2022");
        car.setPrice("$18,699");
        car.setImage("https://res.cloudinary.com/dwsdggfi5/image/upload/v1582924913/Images/vehicle/husqvarna901_bta9pn.jpg");

        cars = new HashSet<>();
        cars.add(car);
        category.setCars(cars);
        categoryRepository.save(category);

        car.setCategory(category);
        carRepository.save(car);

//////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
        /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////

        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));
        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);


    }


    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////
    /////////////////////////////////////////////// SECURITY ///////////////////////////////////////////////






    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public void run(String... strings) throws Exception {
//        roleRepository.save(new Role("USER"));
//        roleRepository.save(new Role("ADMIN"));
//        Role adminRole = roleRepository.findByRole("ADMIN");
//        Role userRole = roleRepository.findByRole("USER");
//
//        User user = new User("jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
//        user.setRoles(Arrays.asList(userRole));
//        userRepository.save(user);
//
//        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
//        user.setRoles(Arrays.asList(adminRole));
//        userRepository.save(user);
//    }
}
