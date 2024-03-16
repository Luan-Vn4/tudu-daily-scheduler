package br.upe.tudu.rest.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "TestController", urlPatterns = "/test/*")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().write("Testing");
        resp.getWriter().flush();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
