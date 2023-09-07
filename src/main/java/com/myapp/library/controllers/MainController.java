package com.myapp.library.controllers;

import com.myapp.library.dao.Sorting;
import com.myapp.library.models.Book;
import com.myapp.library.models.DeliveryInformation;
import com.myapp.library.models.Orders;
import com.myapp.library.models.Person;
import com.myapp.library.repositories.BookRepository;
import com.myapp.library.repositories.DeliveryRepository;
import com.myapp.library.repositories.OrdersRepository;
import com.myapp.library.repositories.PeopleRepository;
import com.myapp.library.services.OrdersNumbers;
import com.myapp.library.services.OrdersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @GetMapping("/")
    public String mainPage(Model model, Principal principal){
        boolean isAuthenticated = (principal != null);
        model.addAttribute("ifAuthenticated", isAuthenticated);
        model.addAttribute("registerMenu", !isAuthenticated);
        return "main";
    }

    @GetMapping("/catalog")
    public String indexPage(Model model, Principal principal){
        boolean isAuthenticated = (principal != null);
        model.addAttribute("ifAuthenticated", isAuthenticated);
        model.addAttribute("registerMenu", !isAuthenticated);

        List<Book> listBooks = bookRepository.findAll();
        model.addAttribute("books", listBooks);

        return "index";
    }
    @GetMapping("/sorted-by-book-name")
    public String sorting(Model model, Principal principal){
        boolean isAuthenticated = (principal != null);
        model.addAttribute("ifAuthenticated", isAuthenticated);
        model.addAttribute("registerMenu", !isAuthenticated);

        List<Book> books = bookRepository.findAll();

        Sorting.sortingByBookName(books);

        model.addAttribute("sortedBooks", books);

        return "sorted";
    }

    @PostMapping("/catalog")
    public String findBookByName(Model model, @RequestParam("book") String bookName){
        Iterable<Book> books;

        if (bookName != null && !bookName.isEmpty()) {
            books = bookRepository.findBookByName(bookName);
        }else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);

        return "index";
    }



    @GetMapping("/buy/{id}")
    public String buyBookPage(@PathVariable("id") int id, Model model, Principal principal){
        model.addAttribute("book", bookRepository.findBookById(id));
        Person person = peopleRepository.findByEmail(principal.getName());
        model.addAttribute("userId", person.getId());
        DeliveryInformation deliveryInformation = new DeliveryInformation();
        model.addAttribute("delivery", deliveryInformation);

        return "buy";
    }

    @PostMapping("/buy/{id}")
    public String order(@PathVariable("id") int bookId, @RequestParam("userId") int userId,
                        @ModelAttribute("delivery") @Valid DeliveryInformation deliveryInformation,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/buy";
        }
        deliveryInformation.setId(0);
        deliveryRepository.save(deliveryInformation);
        Orders order = ordersService.save(userId, bookId, deliveryInformation.getId(), OrdersNumbers.randomNumber());

        return "redirect:/order-details/" + order.getOrderId();
    }

    @GetMapping("/order-details/{id}")
    public String orderDetails(@PathVariable("id") int orderId, Model model){
        model.addAttribute("orderNumber", ordersRepository.findAllByOrderId(orderId));
        Orders order = ordersRepository.findAllByOrderId(orderId);
        model.addAttribute("book", bookRepository.findBookById(order.getBookId()));

        return "showOrderNumber";
    }
}
