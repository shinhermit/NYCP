<%-- 
    Document   : incarcerate
    Created on : 29 sept. 2014, 16:24:46
    Author     : josuah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="content-type" content="application/xhtml+xml; charset=ISO-8859-1" />
        <link href="../css/stylesimple.css" rel="stylesheet" type="text/css" />
        <title>Incarcerate</title>
        <meta content="Franck Barbier" name="author" />
        <meta content="Tutorial" name="description" />
        <meta content="UML, Component-based development, service computing, Java EE"
              name="keywords" />
    </head>
    <body>
        <form action="/NYCP/incarcerate/new" enctype="application/x-www-form-urlencoded" method="post">
            <h1>New York City Penitentiary (© <a href="mailto:Franck.Barbier@FranckBarbier.com">Franck.Barbier@FranckBarbier.com</a>)
                - Incarcerate</h1>
            <h2>
                <bluage:link id="home" scope="page" tovalidate="false" type="dynamic" xmlns:bluage="http://www.bluage.com/2006/v1">
                    <a href="../NYCP_application_management/home_screen.html" shape="rect">Home</a>
                </bluage:link> </h2>
            <table cellspacing="2" cellpadding="2" border="1" style="text-align: left; width: 100%;">
                <tbody>
                    <tr>
                        <th colspan="1" rowspan="1">Prison file number</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="fileNumber" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="fileNumber" /> </bluage:textfield>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="1" rowspan="1">Given name</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="name" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="name" /> </bluage:textfield> </td>
                    </tr>
                    <tr>
                        <th colspan="1" rowspan="1">Surname</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="surname" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="surname" /> </bluage:textfield> </td>
                    </tr>
                    <tr>
                        <th colspan="1" rowspan="1">Date of birth</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="dateOfBirth" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="dateOfBirth" /> </bluage:textfield>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="1" rowspan="1">Place of birth</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="placeOfBirth" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="placeOfBirth" /> </bluage:textfield>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="1" rowspan="1">Date of incarceration</th>
                        <td colspan="1" rowspan="1">
                            <bluage:textfield defaultvalue="" id="dateOfIncarceration" xmlns:bluage="http://www.bluage.com/2006/v1">
                                <input type="text" name="dateOfIncarceration" /> </bluage:textfield>
                        </td>
                    </tr>
                </tbody>
            </table>
            <p><input type="submit" name="submit"/></p>
        </form>

    </body>
</html>
