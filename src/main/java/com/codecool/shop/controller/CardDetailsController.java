package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.service.ApplicationService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

//TODO after creating the order

@WebServlet(name = "cardPayment", urlPatterns = "/card-payment", loadOnStartup = 7)
public class CardDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationService applicationService = ApplicationService.getInstance();

//        OrderService orderService = applicationService.getOrderService();

        CartDao cart = applicationService.getCartDao();


//        CartDao cart= CartDaoMem.getInstance();
//        OrderService orderService = new OrderService();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        context.setVariable("cart", cart.getCart(UUID.randomUUID()));
//        context.setVariable("order", order);

        engine.process("/product/card-payment.html", context, resp.getWriter());
    }
}