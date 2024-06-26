# 3D PRINT SHOP
Semestrální projekt předmětu NNPIA na Univerzitě Pardubice je zasvěcený praktické aplikaci v oblasti vývoje webových aplikací s využitím frameworku Spring Boot a technologie React. Tento projekt podrobně zkoumá procesy návrhu, implementace a nasazení moderních webových aplikací, s důrazem na efektivní využití moderních technologických nástrojů a metodik.

## Obsah

- [Přehled](#přehled)
- [Backend](#backend-srcmainjava)
- [Frontend](#frontend-reactsrc)
- [Obrazky](#obrazky)
- [Testy](#tests-srctest)
- [Další](#other-files)


## Přehled

Vítejte v našem inovativním eshopu, kde vám přinášíme špičkové produkty vyrobené pomocí 3D tisku. Nabízíme široký výběr unikátních a vysoce kvalitních tisknutých produktů, které spojují technologii s tvůrčím uměním. Naše nabídka vám umožní transformovat vaše nápady do skutečnosti a objevit nové možnosti pro osobní design a funkčnost. Každý produkt v našem eshopu má svůj vlastní příběh a přináší inspiraci pro každodenní použití. Jsme tu, abychom vám poskytli jedinečné zážitky a inspiraci prostřednictvím 3D tisku.

## Backend `src.main.java.app.eshop`

#### WebApplication
definuje hlavní třídu pro spuštění webové aplikace ve frameworku Spring Boot.

### Package `config`

#### AllConfiguration
konfigurační třída Spring MVC pro e-shop, která zajišťuje správu statických obrázků, CORS konfiguraci a definici beans pro šifrování hesel a správu multipartních požadavků.
#### SecurityConfiguration
konfigurační třída pro Spring Security v e-shopu, která definuje filtry pro JWT autentizaci, správu uživatelů a zabezpečení API endpointů pomocí autorizačních pravidel.

### Package `controller`

#### CartController
REST kontroler pro správu nákupního košíku v e-shopu, který umožňuje přidávání a odebírání produktů z košíku pomocí HTTP POST metod. Dále poskytuje endpoint pro získání seznamu produktů v košíku prostřednictvím HTTP GET metod.
#### CustomerOrderController
REST kontroler pro správu objednávek zákazníků v e-shopu, který zpracovává HTTP POST požadavky na ověření platnosti autorizačního tokenu a následné ověření objednávky zákazníka.
#### NotFoundException
je výjimka v rámci kontrolerů aplikace e-shopu, která je vyvolána v případě, že žádost o zdroj nebyla nalezena (HTTP status kód 404).
#### ProductController
REST kontroler pro manipulaci s produkty v aplikaci e-shopu, který obsahuje metody pro získání všech produktů, získání konkrétního produktu podle ID, vytvoření nového produktu včetně obrázku, aktualizaci existujícího produktu a smazání produktu podle ID.


### Package `dao`
#### UserDAO
repozitářní třída v aplikaci e-shopu, která poskytuje metodu pro vyhledávání uživatelů podle uživatelského jména. Využívá PasswordEncoder pro šifrování hesel uživatelů a definuje statický seznam uživatelských účtů s různými rolími pro účely demonstrace autentizace pomocí Spring Security.

### Package `dto`

#### AuthRequestDTO
jednoduchý datový objekt v aplikaci e-shopu, sloužící k předávání autentizačních požadavků obsahující uživatelské jméno a heslo.

#### CartProductDTO
datový objekt v aplikaci e-shopu, který reprezentuje produkt v nákupním košíku spolu s jeho množstvím.

#### ProductDTO
datový objekt v aplikaci e-shopu, který slouží k předávání informací o novém produktu včetně názvu, popisu, obrázku a ceny.
#### ServerProductDTO
datový objekt v aplikaci e-shopu, který reprezentuje základní informace o produktu, včetně ID, názvu, popisu, cesty k obrázku a ceny. 


### Package `entity`
#### CustomerOrder
je entitní třídou reprezentující objednávku zákazníka v aplikaci e-shopu. Obsahuje informace jako id objednávky, uživatelské jméno, stav objednávky
#### CustomerOrder_Product
je entitní třídou reprezentující spojení mezi objednávkou zákazníka a produktem v aplikaci e-shopu. Obsahuje informace o id spojení, objednávce, produktu a množství daného produktu v objednávce.
#### CustomerOrderStateEnum
tento výčtový typ definuje stav objednávky v aplikaci e-shopu. Možné stavy zahrnují "NEW" pro nové objednávky, "PAID" pro zaplacené objednávky a "DELIVERED" pro objednávky, které byly doručeny zákazníkovi.
#### Product
představuje entitu produktu v aplikaci e-shopu, obsahující atributy jako název produktu, popis, cena, cesta k obrázku a kolekce, které propojují tento produkt s objednávkami zákazníků. 


### Package `repository`

obsahuje CustomerOrder_ProductRepository, CustomerOrderRepository a ProductRepository datové vrstvy, které umožňují provádět persistance nad entitami v databázi pomocí frameworku Spring Data JPA. Databázové schéma je k dispozici v rootu projektu s názvem DB_Schema.png

### Package `runner`
obsahuje třídu WebRunner, která slouží k inicializaci databáze při spuštění aplikace e-shopu, přičemž ukládá několik produktů a jednu zákaznickou objednávku s propojeným produktem. Tato třída využívá repository rozhraní k provedení operací persistenci nad entitami Product, CustomerOrder a CustomerOrder_Product.

### Package `security`
#### AuthenticationController
poskytuje endpoint pro autentizaci uživatelů, kde metoda authenticate zpracovává přihlašovací požadavky a ověřuje uživatelské údaje pomocí AuthenticationManager. Pokud jsou přihlašovací údaje platné, generuje a vrací JWT token pomocí utility JWTUtilities; v opačném případě vrací chybovou zprávu.
#### JWTFilter
je filtrem, který se spouští při každém příchozím požadavku a ověřuje platnost JWT tokenu v hlavičce požadavku, přičemž autentizuje uživatele, pokud je token platný. Pokud je token validní a uživatel není autentizován, načte uživatelské údaje a nastaví autentizační informace do SecurityContextHolder, čímž umožní zabezpečený přístup k chráněným prostředkům aplikace.

#### JWTUtilities
je komponentou pro práci s JWT tokeny, poskytující metody pro jejich generování, ověřování a extrakci informací. Obsahuje metody pro extrakci uživatelského jména a dalších claimů z tokenu, ověření platnosti tokenu, generování nových tokenů s uživatelskými údaji a kontrolu, zda token již nevypršel. Tokeny jsou podepisovány pomocí HMAC SHA-256 algoritmu a mají dobu platnosti 24 hodin.

### Package `service`
#### CartService a CartServiceImpl
poskytuje metody pro přidávání a odstraňování produktů z košíku a zobrazení aktuálního obsahu košíku. Produkty jsou spravovány pomocí seznamu CartProductDTO, který uchovává produkty a jejich množství, a interaguje s repozitářem produktů ProductRepository pro získání detailů o produktech podle jejich ID.

#### CustomerOrderService a CustomerOrderServiceImlp
poskytuje metody pro vytvoření nové objednávky z obsahu košíku a zobrazení všech objednávek konkrétního zákazníka. Při vytvoření nové objednávky se uloží objednávka a její položky do příslušných repozitářů, přičemž se následně vyprázdní košík.

#### FileService a FileServiceImpl
třída, která implementuje metodu pro nahrávání obrázků. Metoda upload uloží nahraný obrázek do adresáře "images" a v případě úspěchu vrátí jeho původní název, přičemž při chybě vyvolá výjimku.

#### ProductService a ProductServiceImpl
poskytuje metody pro vytváření, aktualizaci, mazání a získávání produktů. Service rovněž podporuje stránkování a třídění při získávání všech produktů a integruje nahrávání souborů pro správu obrázků produktů.

### Package `validation`

#### ImageFileValidator
implementuje validátor souborů obrázků ve Spring frameworku, který ověřuje, zda má soubor povolený MIME typ (JPEG, PNG nebo GIF). Pokud soubor nemá platný typ nebo je prázdný, validátor vrátí hodnotu false.

#### ValidImage
definuje vlastní validaci pomocí anotace @ValidImage v aplikaci Java s využitím technologií Jakarta Validation 


## Frontend `react.src`

#### Apk.jsx a Apk.css
komponeneta zahrnují načítání, zobrazení a řízení produktů, nákupní košík s možností přidání a odebrání produktů, navigaci mezi různými stránkami pomocí React Router.

#### authcontext.jsx
vytváří kontext pro autentizaci v React aplikaci. Ukládá stav autentizace na základě existence tokenu v localStorage a poskytuje ho jako kontext pro potomky komponenty AuthProvider pomocí useContext hooku, aby mohly získat informace o stavu autentizace.

#### cart.jsx a cart.css
implementuje komponentu Cart v React, která zobrazuje obsah nákupního košíku uživatele. Komponenta využívá kontext autentizace pro přesměrování uživatele na stránku přihlášení nebo potvrzení objednávky podle stavu autentizace. Uživatel může přidávat nebo odebírat produkty přímo z košíku, a celková cena objednávky se dynamicky aktualizuje.


#### confirm-order.jsx
implementuje komponentu ConfirmOrder v React, která odesílá objednávku na server pomocí POST požadavku s autorizačním tokenem uloženým v localStorage. Po odeslání se zobrazí informace o úspěšném odeslání objednávky. 

#### detail.jsx a detail.css
definuje komponentu Detail v React, která zobrazuje detailní informace o produktu na základě parametru id z URL. Komponenta načítá data produktu z API a umožňuje uživateli přidat produkt do košíku nebo přejít na stránku pro úpravu produktu. Stav isInCart označuje, zda je produkt již v košíku.

#### login.jsx a login.css
implementuje komponentu Login v React, která zobrazuje formulář pro přihlášení uživatele pomocí uživatelského jména a hesla. Po odeslání formuláře provede žádost o přihlášení na server pomocí POST požadavku na URL /auth/login. Pokud je přihlášení úspěšné, uloží se autentizační token do localStorage a aktualizuje se stav autentizace pomocí setIsAuthenticated z kontextu AuthContext, následně uživatele přesměruje na hlavní stránku.

#### navbar.jsx a navbar.css
definuje navigační panel (NavBar) v React, který zobrazuje odkazy na různé části aplikace jako domovskou stránku, formulář pro produkty, nákupní košík a možnost přihlášení nebo odhlášení uživatele. Podle stavu isAuthenticated z kontextu AuthContext mění zobrazení odkazu na "Přihlásit se" nebo "Odhlásit se". Funkce handleLogout odstraňuje autentizační token z localStorage a nastavuje isAuthenticated na false, poté uživatele přesměruje zpět na domovskou stránku.

#### pagination.jsx a pagination.css
poskytuje ovládací prvky pro navigaci mezi stránkami produktů a volbu počtu produktů na stránku. Obsahuje tlačítka pro předchozí a další stránku, informaci o aktuální stránce a možnost volby velikosti stránky s produkty. 

#### product.jsx a product.css
zobrazuje jednotlivý produkt s názvem, obrázkem, cenou a odkazem na detail produktu. Produkt je prezentován pomocí vlastností získaných z prop product

#### product-form.jsx a product-form.css
implementuje formulář pro přidávání nebo úpravu produktu v React.

#### sort.jsx a sort.css
zobrazuje rozhraní pro výběr a změnu způsobu řazení produktů podle vybraného kritéria. Uživatel může vybírat mezi možnostmi řazení podle ID produktu, názvu produktu nebo ceny.

## Obrazky `images`
jedná se o složku, ve které jsou uloženy obrázky produktů, kterým je v databázi přiřazen jednotlivý produkt. Při přidání produktů pomocí formuláře jsou nové obrázky uchovány v této složce.


## Testy `src.test.java.app.eshop`


#### CartServiceTests
ověřují funkcionalitu třídy CartServiceImpl pomocí mockovaného ProductRepository v Mockito. Testy zahrnují přidávání a odebírání produktů z košíku, kontrolu jejich množství a správnost chování při manipulaci s obsahem košíku na základě identifikátorů produktů.
#### EshopApplicationTests
hlavní třída testů, neobsahuje nic zajímavého
#### IntegrationTests
integrační test ověřuje správnost vytváření objednávek zákazníků v aplikaci e-shopu pomocí služeb CartService a CustomerOrderService. Testuje se přidání produktů do košíku, kontrola vytvoření objednávky a správnost jejího obsahu včetně množství jednotlivých produktů.
#### ProductControllerTests
obsahuje sadu unit testů pro ProductController, které ověřují správné chování různých scénářů v aplikaci. Testy zahrnují vytváření nového produktu s obrázkem, zpracování neplatného obrázku a získání existujícího produktu podle identifikátoru s ověřením vrácených atributů.


## Další

#### `src.main.resources` application.yaml
Aplikace Spring s názvem 'eshop' se připojuje k databázi PostgreSQL na localhostu na portu 5432.
Hibernate je nastaven tak, aby při spuštění aplikace automaticky vytvářel a mazal schéma databáze; SQL dotazy jsou zobrazeny v konzoli díky volbě 'show-sql'.
#### docker-compose.yaml
Vytváří:

kontejner s názvem eshopDB, který používá image databáze Postgres. Databáze PostgreSQL vytvořená v tomto kontejneru se jmenuje eshop.

kontejner s názvem eshopReact, založený na node image. Pracovní adresář uvnitř kontejneru je nastaven na /react, který je propojen se složkou ./react na hostitelském stroji. Aplikace React v kontejneru je nakonfigurována tak, že při spuštění provede instalaci závislostí pomocí npm, včetně react-router-dom, a spustí vývojový server pomocí příkazu npm run dev.

#### build.gradle
soubor definuje konfiguraci pro projekt využívající Spring Boot verze 3.1.8 a JDK verze 17. Obsahuje závislosti na různých modulích Spring Bootu pro práci s JPA, Thymeleaf, validacemi, zabezpečením a dalšími funkcionalitami. Dále používá PostgreSQL jako běhovou závislost pro aplikaci a integruje knihovnu JJWT pro práci s JSON Web Tokeny.