package com.example.essence.service;

import com.example.essence.model.product.Category;
import com.example.essence.model.product.Color;
import com.example.essence.model.product.Product;
import com.example.essence.model.product.Type;
import com.example.essence.model.user.User;
import com.example.essence.repo.product.CategoryRepository;
import com.example.essence.repo.product.ProductRepository;
import com.example.essence.repo.product.TypeRepository;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Value("${product.img.path}")
    private String productsImgPath;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public Product getById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllLimit(int limit) {
        return entityManager.createQuery("SELECT p FROM Product p ORDER BY p.loading_date",
                Product.class).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Product> getAllSortedBySoldAndLimit(int limit) {
        return entityManager.createQuery("SELECT p FROM Product p ORDER BY p.sold DESC",
                Product.class).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Product> search(String searchQuery) {
        Set<Product> searchProducts = new LinkedHashSet<>();

        StringTokenizer stringTokenizer = new StringTokenizer(searchQuery);

        searchProducts.addAll( productRepository.findByNameLike('%'+searchQuery+'%') );

        while (stringTokenizer.hasMoreTokens()){
            String token = '%' + stringTokenizer.nextToken() + '%';

            searchProducts.addAll( searchByCategoryLike(token) );
            searchProducts.addAll( searchByTypeLike(token) );
            searchProducts.addAll( searchByColorLike(token) );
            searchProducts.addAll( productRepository.findByNameLike(token) );
        }

        return new ArrayList<>(searchProducts);
    }

    @Override
    public List<Product> searchByFilter(List<Product> products, Double min_price, Double max_price, Color color) {
        if( min_price != null){
            products.removeIf(product -> !(product.getPrice() > min_price));
        }
        if ( max_price != null){
            products.removeIf(product -> !(product.getPrice() < max_price));
        }
        if (color != null){
            int a = 1;
            products.removeIf(product -> !(product.getColors().contains(color)));
        }

        return new ArrayList<>( products );
    }

    @Override
    public List<Product> searchByFilter(Category category, Type type, Double min_price, Double max_price, Color color) {
        List<Product> products = new LinkedList<>( searchByCategoryAndType(category, type) );

        return searchByFilter(products, min_price, max_price, color);
    }

    @Override
    public List<Product> searchByCategory(Category category) {
        List<Product> products = productRepository.findByCategories(category);
        return products;
    }

    @Override
    public List<Product> searchByCategoryLike(String categoryName) {
        String query = "SELECT p FROM Product p INNER JOIN p.categories c WHERE c.name LIKE :name";
        return searchByFeature(categoryName, query);
    }

    @Override
    public List<Product> searchByColor(Color color) {
        List<Product> products = productRepository.findByColors(color);
        return products;
    }

    @Override
    public List<Product> searchByColorLike(String colorName) {
        String query = "SELECT p FROM Product p INNER JOIN p.colors c WHERE c.name LIKE :name";
        return searchByFeature(colorName, query);
    }

    @Override
    public List<Product> searchByType(Type type) {
        List<Product> products = productRepository.findByTypes(type);
        return products;
    }

    @Override
    public List<Product> searchByTypeLike(String typeName) {
        String query = "SELECT p FROM Product p INNER JOIN p.types t WHERE t.name LIKE :name";
        return searchByFeature(typeName, query);
    }

    @Override
    public List<Product> searchByCategoryAndType(Category category, Type type) {
        List<Product> products = productRepository.findByCategoriesAndTypes(category, type);
        return products;
    }

    @Override
    public void save(String name, Double price, MultipartFile[] list_img) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setSold(0);
        product.setLoading_date(new Date());
        save(product, list_img);
    }

    @Override
    @Transactional
    public void save(Product product, MultipartFile[] list_img){
        try{
            productRepository.save(product);
            saveProductImages(product, list_img);
        } catch (Exception e){
            deleteProductImages(product);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Product product) throws IOException {
        productRepository.delete(product);
        deleteProductImages(product);
    }



    // _________________subsidiary functions_____________________________________________________________________
    public void saveProductImages(Product product, MultipartFile[] list_img) throws Exception {
        if (list_img != null) {
            // creating images directory
            File uploadDir = new File(productsImgPath + '/' + product.getId());
            if (uploadDir.exists())
                throw new RuntimeException("product id already exists");

            // saving images in directory
            boolean mkdir = uploadDir.mkdir();
            int imgNumber = 0;
            for (MultipartFile file : list_img) {
                if (file != null && !file.getOriginalFilename().isEmpty()) {
                    String filePermissions = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
                    String fileName = "img" + imgNumber + filePermissions;
                    file.transferTo(new File(uploadDir + "/" + fileName));
                    ++imgNumber;
                }
            }
        }
    }

    public void deleteProductImages(Product product) {
        String productImgFolderPath = productsImgPath + '/' + product.getId();
        File productImgFolder = new File(productImgFolderPath);

        if (productImgFolder.exists()) {
            try {
                FileUtils.deleteDirectory(productImgFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Product> searchByFeature(String name, String query) {
        StringTokenizer stringTokenizer = new StringTokenizer(name);
        Set<Product> resultList = new LinkedHashSet<>();

        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            resultList.addAll(entityManager
                    .createQuery(query, Product.class)
                    .setParameter("name", token.toUpperCase())
                    .getResultList());
        }

        return new ArrayList<>(resultList);
    }

}
