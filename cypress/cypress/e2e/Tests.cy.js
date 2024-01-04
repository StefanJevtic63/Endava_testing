///<reference types="Cypress"/>

describe('Provera kupovine proizvoda', () => {
    let randomUser;

    beforeEach(() => {
        cy.fixture('users').then((data) => {
            let users = data.users;
            if (users.length > 0) {
                randomUser = users[Math.floor(Math.random() * users.length)];
            } else {
                throw new Error('No users found in fixture');
            }
        });
    });

    beforeEach(() => {
        cy.visit('https://www.saucedemo.com');
    });

    
    // TestCase1
    it('Provera prikazivanja poruke o grešci nakon neuspešnog prijavljivanja', () => {
        cy.get('[data-test=username]').type('standard_user');
        cy.get('[data-test=password]').type('neispravna_lozinka');
        cy.get('[data-test=login-button]').click();

        // Provera da se pojavljuje greška sa tekstom: 
        // Epic sadface: Username and password do not match any user in this service
        cy.get('[data-test=error]').should('have.text', 
                'Epic sadface: Username and password do not match any user in this service');
    });
    
    // TestCase1.5
    it('Provera prikazivanja poruke o grešci nakon prijavljivanja sa nestandardnim korisnikom', () => {
        cy.get('[data-test=username]').type(randomUser.username);
        cy.get('[data-test=password]').type(randomUser.password);
        cy.get('[data-test=login-button]').click();

        // Ako je locked_out_user onda se treba pojaviti odgovarajuca poruka o gresci
        if (randomUser.username == 'locked_out_user') {
            cy.get('[data-test=error]').should('have.text', 
            'Epic sadface: Sorry, this user has been locked out.');
        }
        else {
            // Sve ostale vrednosti za username ce se uspesno prijaviti na stranicu
            // tako da se za svakog korisnika javlja odredjena greska-neuobicajeno ponasanje
            cy.url().should('include', '/inventory');
        }

    });

    // TestCase2
    it('Provera prijavljivanja sa ispravnim podacima', () => {
        // Prijavljivanje na stranicu
        cy.get('[data-test=username]').type('standard_user');
        cy.get('[data-test=password]').type('secret_sauce');
        cy.get('[data-test=login-button]').click();

        // Provera URL-a stranice - inventory
        cy.url().should('include', '/inventory');
    });

    // TestCase3
    it('Provera sortiranja inventara', () => {
        // Prijavljivanje na stranicu
        cy.get('[data-test=username]').type('standard_user');
        cy.get('[data-test=password]').type('secret_sauce');
        cy.get('[data-test=login-button]').click();

        // Provera URL-a stranice - inventory
        cy.url().should('include', '/inventory');

        // Iz padajuće liste za sortiranje podataka biramo 'Name Z to A' 
        cy.get('[data-test=product_sort_container]').select('za')

        // Prvi proizvod mora imati naziv ‘Test.allTheThings() T-Shirt (Red)’.
        cy.get('.inventory_item_name').first().should('have.text', 'Test.allTheThings() T-Shirt (Red)')
    });
    
    // TestCase4
    it('Provera kupovine proizvoda', () => {
        // Prijavljivanje na stranicu
        cy.get('[data-test=username]').type('standard_user');
        cy.get('[data-test=password]').type('secret_sauce');
        cy.get('[data-test=login-button]').click();
    
        // Provera URL-a stranice - inventory
        cy.url().should('include', '/inventory');

        // Dodavanje proizvoda u korpu
        cy.get('[data-test="add-to-cart-test.allthethings()-t-shirt-(red)"]').click();
    
        // Potvrda da je proizvod u korpi
        cy.get('.shopping_cart_link').click();
        cy.get('.cart_item').should('have.length', 1);
    
        // Checkout
        cy.get('[data-test=checkout]').click();
    
        // Upisivanje imena, prezimena, poštanskog broja i potom klik na dugme ‘Continue’
        cy.get('[data-test=firstName]').type('Ime');
        cy.get('[data-test=lastName]').type('Prezime');
        cy.get('[data-test=postalCode]').type('11000');
        cy.get('[data-test=continue]').click();
    
        // Klik na dugme ‘Finish’
        cy.get('[data-test=finish]').click();
    
        // Potvrda poruke o uspešnoj kupovini
        cy.get('.complete-header').should('have.text', 'Thank you for your order!');
    });
    
});
