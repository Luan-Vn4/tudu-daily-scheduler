package br.upe.tudu.rest.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "TaskController", urlPatterns = "/api/tasks/*")
public class TaskController extends HttpServlet {
}
