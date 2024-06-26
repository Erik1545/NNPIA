package app.eshop.runner;
import app.eshop.entity.CustomerOrder;
import app.eshop.entity.CustomerOrderStateEnum;
import app.eshop.entity.CustomerOrder_Product;
import app.eshop.entity.Product;
import app.eshop.repository.CustomerOrderRepository;
import app.eshop.repository.CustomerOrder_ProductRepository;
import app.eshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@AllArgsConstructor
@Component
public class WebRunner implements ApplicationRunner {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    private final CustomerOrder_ProductRepository customerOrderProductRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Product product1 = new Product();
        product1.setProductName("Iron Man");
        product1.setImagePath("iron-man.png");
        product1.setDescription("Doba tisku: Něco málo přes 4 hodiny\n" +
                "Nastavení tisku: Střední rozlišení/střední rychlost\n" +
                "Tiskárna: Mamba 3D\n" +
                "Měřítko objektu: X Změněno na 1,3\n" +
                "Výška objektu: 10,5 cm vč. základna\n" +
                "Vytištěno pomocí: PLA_1.75\n" +
                "Celkový počet vrstev: 683\n" +
                "Celkem řádků: 172035\n" +
                "Potřebné vlákno: 6484 mm\n" +
                "Chyby: Mírná chyba na pravé ruce (pouze prsty)");
        product1.setPrice(56);

        Product product2 = new Product();
        product2.setProductName("Spiderman");
        product2.setImagePath("spiderman.webp");
        product2.setDescription("Nastavení tisku\n" +
                "Značka tiskárny:\n" +
                "Prusa\n" +
                "\n" +
                "tiskárna:\n" +
                "i3 MK2 MMU\n" +
                "\n" +
                "Rafty:\n" +
                "Ano\n" +
                "Podporuje:\n" +
                "Ano\n" +
                "Rozlišení:\n" +
                "1.5\n" +
                "Výplň:\n" +
                "20\n");
        product2.setPrice(55);

        Product product3 = new Product();
        product3.setProductName("Black Widow");
        product3.setImagePath("black-widow.webp");
        product3.setDescription("tiskárna:\n" +
                "Jaycar TL4100\n" +
                "\n" +
                "Rafty:\n" +
                "Ano\n" +
                "Podporuje:\n" +
                "Ano\n" +
                "Rozlišení:\n" +
                "0,1 mm\n" +
                "Výplň:\n" +
                "5 %\n" +
                "Poznámky:\n" +
                "Vytiskl jsem ji vzhůru nohama, abych minimalizoval oporu na rukou a nohou.\n" +
                "\n" +
                "Rád bych šel větší, ale ostatní figurky v sadě byly vysoké asi 80 mm, takže jsem ji nastavil.");
        product3.setPrice(24);

        Product product4 = new Product();
        product4.setProductName("Kapitan Amerika");
        product4.setImagePath("kapitan.png");
        product4.setDescription("Značka tiskárny:\n" +
                "Wanhao\n" +
                "\n" +
                "tiskárna:\n" +
                "Wanhao Duplicator i3 V2\n" +
                "\n" +
                "Rafty:\n" +
                "Na tom nezáleží\n" +
                "Podporuje:\n" +
                "Ano\n" +
                "Rozlišení:\n" +
                "0,2 nebo 0,1\n" +
                "Výplň:\n" +
                "30 %\n" +
                "Poznámky:\n" +
                "Tento model můžete tisknout v rozlišení 0,2 nebo 0,1 mm s tryskou 0,4 až 0,2 mm.");
        product4.setPrice(57);

        Product product5 = new Product();
        product5.setProductName("Groot");
        product5.setImagePath("groot.png");
        product5.setDescription("Značka tiskárny:\n" +
                "MakerBot\n" +
                "\n" +
                "tiskárna:\n" +
                "Replikátor MakerBot\n" +
                "\n" +
                "Rafty:\n" +
                "Na tom nezáleží\n" +
                "Podporuje:\n" +
                "Ne\n" +
                "Rozlišení:\n" +
                "Nezáleží na tom.\n" +
                "Výplň:\n" +
                "20 %\n" +
                "Poznámky:\n" +
                "Někteří uživatelé hlásili problémy s použitím méně než 20% výplně. 20 % tiskne v pořádku.");
        product5.setPrice(13);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);

        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setState(CustomerOrderStateEnum.NEW);
        customerOrderRepository.save(customerOrder);

        CustomerOrder_Product customerOrder_product = new CustomerOrder_Product();
        customerOrder_product.setProduct(product1);
        customerOrder_product.setCustomerOrder(customerOrder);
        customerOrder_product.setQuantity(5);
        customerOrderProductRepository.save(customerOrder_product);
    }
}
