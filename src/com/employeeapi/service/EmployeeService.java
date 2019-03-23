package com.employeeapi.service;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.employeeapi.dao.EmployeeDAO;
import com.employeeapi.vo.EmployeeVO;

@Path("/employee")
public class EmployeeService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response index() {
		EmployeeDAO dao = new EmployeeDAO();
		List<EmployeeVO> employees = dao.getList();
		return Response.ok(employees).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")	
	public Response create(EmployeeVO vo) {
		EmployeeDAO dao = new EmployeeDAO();
		vo = dao.save(vo);
		return Response.ok(vo).build();
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")	
	public Response update(EmployeeVO vo) {
		EmployeeDAO dao = new EmployeeDAO();
		vo = dao.update(vo);
		return Response.ok(vo).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") int id) {
		EmployeeDAO dao = new EmployeeDAO();
		dao.delete(id);
		return Response.ok().build();
	}
}
