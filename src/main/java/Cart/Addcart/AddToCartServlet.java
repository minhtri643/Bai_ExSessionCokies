package Cart.Addcart;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import Cart.product.Product;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import Cart.product.Product;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin sản phẩm từ request
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        
        // Tạo đối tượng sản phẩm
        Product product = new Product(productName, price, quantity);
        
        // Lấy session hiện tại
        HttpSession session = request.getSession();
        
        // Lấy giỏ hàng hiện tại từ session hoặc tạo mới nếu chưa có
        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        // Thêm sản phẩm vào giỏ hàng
        cart.add(product);
        
        // Cập nhật giỏ hàng trong session
        session.setAttribute("cart", cart);
        
        // Chuyển hướng về trang giỏ hàng
        response.sendRedirect("cart.jsp");
    }
}

