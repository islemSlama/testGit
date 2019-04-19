package fr.eni.javaee.module2;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeuxiemeServlet
 */
@WebServlet(description = "Deuxieme Servlet", urlPatterns = { "/deuxieme" }, initParams = {

		@WebInitParam(description = "valeur_max", name = "max", value = "11"),
		@WebInitParam(description = "essais_max", name = "essais", value = "4") }

)

public class DeuxiemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean win;
	private int nbreInit = 0;
	private int nbreGet = 0;
	private int nbreDestroy = 0;
	private static int valeur;
	private static int tour = 0;

	// private int bornemin =0;
	// private int bornemax=10;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeuxiemeServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		this.nbreInit += 1;
		// on peut aussi faire ça pour generer un nombre aléatoire :
		// this.valeur= new Random().nextInt(this.bornemax-this.bornemin)+this.bornemin;
		// on peut aussi faire ça pour generer un nombre aléatoire :
		// this.valeur= new Random().nextInt(11);

		// String valeurMax =this.getInitParameter("max");
		this.valeur = new Random().nextInt(Integer.parseInt(this.getInitParameter("max")));
		this.win = false;
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.nbreGet++;
		response.sendRedirect(request.getContextPath() + "/index.html");
		// TODO Auto-generated method stub
		// response.getWriter().append("Nombre Init: " + this.nbreInit + "\n");
		// response.getWriter().append("Nombre get: " + this.nbreGet + "\n");
		// response.getWriter().append("Nombre destroy: " + this.nbreDestroy + "\n");
		//
		// int port = request.getServerPort();
		// String urlServlet = request.getServletPath();
		// String methode = request.getMethod();
		// response.getWriter().append("port: " + port + " " + "et UrlServlet: " + " " +
		// urlServlet + "\n");
		// response.getWriter().append(methode);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().println("valeur du random: " + valeur);

		String input = request.getParameter("input");

		tour++;

		if (tour < Integer.parseInt(this.getInitParameter("essais"))) {
			if (Integer.parseInt(input) < valeur) {
				response.sendRedirect(request.getContextPath() + "/EchecPlusPetit.html");
			}
			if (Integer.parseInt(input) > valeur) {
				response.sendRedirect(request.getContextPath() + "/EchecPlusGrand.html");
			}
			if (Integer.parseInt(input) == valeur) {
				response.sendRedirect(request.getContextPath() + "/Gagner.html");
				this.win = true;
			}

		} else {
			response.getWriter().println("vous avez essayez plus que 3 fois, à la prochaine!!!!!!!");
		}

	}
}
