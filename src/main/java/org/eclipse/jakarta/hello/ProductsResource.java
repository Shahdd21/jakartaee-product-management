package org.eclipse.jakarta.hello;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/products")
public class ProductsResource extends HttpServlet {

    Map<String, Product> products = new HashMap<>(Map.of(
            "Shaan body milk", new Product("Shaan body milk", 200),
            "Eva lotion", new Product("Eva lotion", 150),
            "Care and More cream", new Product("Care and More cream", 50),
            "Pure cream", new Product("Pure cream", 20)
    ) );


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();

        out.println(products.values());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        if(products.containsKey(name.toLowerCase())) response.getWriter().println("<p> Product already exists ! </p>");

        else {
            Product product = new Product(name, price);
            products.put(name, product);

            response.getWriter().println("<p> Product is added successfully ! </p>");
        }
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        if(!products.containsKey(name.toLowerCase())) response.getWriter().println("<p> Product does not exist! </p>");

        else {
            Product product = products.get(name);
            product.setPrice(price);

            products.put(name, product);

            response.getWriter().println("<p> Product is updated successfully ! </p>");
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");

        if(!products.containsKey(name.toLowerCase())) response.getWriter().println("<p> Product does not exist! </p>");

        else {
            products.remove(name);
            response.getWriter().println("<p> Product is deleted successfully ! </p>");
        }
    }

}

