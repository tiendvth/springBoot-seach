package com.aptech.springbootseach.seeder;

import com.aptech.springbootseach.entity.Product;
import com.aptech.springbootseach.repository.ProductRepository;
import com.aptech.springbootseach.util.NumberUtil;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

@Component
public class ProductSeeder implements CommandLineRunner {
    @Autowired
    ProductRepository productRepository;

    private static final int NUMBER_OF_PRODUCT = 1000;

    public static ArrayList<Product> products;

    public void generate() {
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");
        genders.add("Other");
        ArrayList<String> size = new ArrayList<>();
        size.add("XS");
        size.add("S");
        size.add("M");
        size.add("L");
        size.add("XL");
        size.add("XXL");
        ArrayList<String> color = new ArrayList<>();
        color.add("blue");
        color.add("black");
        color.add("read");
        color.add("yellow");
        color.add("white");
        Random random = new Random();
        Faker faker = new Faker();
        products = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++) {
            Product product = Product.builder()
                    .name(faker.name().name())
                    .description(faker.name().title())
                    .gender(genders.get(NumberUtil.getRandomNumber(0, genders.size() - 1)))
                    .size(size.get(NumberUtil.getRandomNumber(0, size.size() - 1)))
                    .color(color.get(NumberUtil.getRandomNumber(0, color.size() - 1)))
                    .price(NumberUtil.getRandomNumber(10000, 100000))
                    .createdAt(LocalDateTime.now().minusDays(NumberUtil.getRandomNumber(1,30)))
                    .status(NumberUtil.getRandomNumber(0,1))
                    .build();
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    public void run(String... args) throws Exception{
        generate();
    }
}
