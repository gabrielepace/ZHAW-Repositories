<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<form action="" method="get" id="seachEmployeeForm" role="form">
    <input type="hidden" name="action" value="search">
    <div class="form-group col-xs-5">
        <input type="text" name="employeeName" class="form-control" placeholder="Type the Name or Last Name of the employee" />
    </div>
    <button type="submit" class="btn btn-info">
        <span class="glyphicon glyphicon-search"></span>Search
    </button>
    <br> <br>
</form>