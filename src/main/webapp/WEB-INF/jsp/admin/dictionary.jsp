<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Admin page</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<%@include file="../include/admin_header.jsp" %>
</head>
<body>


        <!-- Left Panel -->

    <%@include file="../include/admin_modules.jsp" %><!-- /#left-panel -->

    <!-- Left Panel -->

    <!-- Right Panel -->

    <div id="right-panel" class="right-panel">

        <!-- Header-->
        <header id="header" class="header">

            <div class="header-menu">

                <div class="col-sm-7">
<!--                     <div class="header-left">
                        <button class="search-trigger"><i class="fa fa-search"></i></button>
                        <div class="form-inline">
                            <form class="search-form">
                                <input class="form-control mr-sm-2" type="text" placeholder="Search ..." aria-label="Search">
                                <button class="search-close" type="submit"><i class="fa fa-close"></i></button>
                            </form>
                        </div>
                    </div> -->
                </div>

                <div class="col-sm-5">
                    <div class="user-area dropdown float-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <form action="<c:url value="/admin/logout" />" method="post">
                                <input type="submit" class="btn btn-default" value="Çıxış"/>
                            </form>
                        </a>

                        <div class="user-menu dropdown-menu">
                                <a class="nav-link" href="#"><i class="fa fa- user"></i>My Profile</a>

                                <a class="nav-link" href="#"><i class="fa fa- user"></i>Notifications <span class="count">13</span></a>

                                <a class="nav-link" href="#"><i class="fa fa -cog"></i>Settings</a>

                                <a class="nav-link" href="#"><i class="fa fa-power -off"></i>Logout</a>
                        </div>
                    </div>

                    <div class="language-select dropdown" id="language-select">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown"  id="language" aria-haspopup="true" aria-expanded="true">
                            <i class="flag-icon flag-icon-us"></i>
                        </a>
                        <div class="dropdown-menu" aria-labelledby="language" >
                            <div class="dropdown-item">
                                <a tabindex="-1" href="<c:url value="?lang=az" />"><span class="flag-icon flag-icon-az"></span></a>
                            </div>
                            <div class="dropdown-item">
                                <a tabindex="-1" href="<c:url value="?lang=en" />"><span class="flag-icon flag-icon-en"></span></a>
                            </div>
                            <div class="dropdown-item">
                                <a tabindex="-1" href="<c:url value="?lang=ru" />"><span class="flag-icon flag-icon-ru"></span></a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

        </header><!-- /header -->
        <!-- Header-->

        <div class="breadcrumbs">
            <div class="col-sm-4">
                <div class="page-header float-left">
                    <div class="page-title">
                        <h1>Soraqçalar</h1>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div class="page-header float-right">
                    <div class="page-title">
                        <ol class="breadcrumb text-right">
                            <li class="active"><span class="fa fa-plus add-dictionary" style="cursor: pointer;"></span></li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>

        <div class="content mt-3" >
            <div class="row">
                
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Soraqça tipi</strong>
                        </div>
                        <div class="card-body">
                            <table id="dictionary_type" class="table table-striped table-bordered">
                                <thead>
                                  <tr>
                                    <th>No</th>
                                    <th>Adı</th>
                                  </tr>
                                </thead>
                                <tbody>
                                    
                                </tbody>
                              </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-8 col-md-offset-1">
                    <div class="card">
                        <div class="card-header">
                            <strong class="card-title">Soraqça</strong>
                        </div>
                        <div class="card-body">
                            <table id="dictinary_list" class="table table-striped table-bordered">
                                <thead>
                                  <tr>
                                    <th>No</th>
                                    <th>AZ</th>
                                    <th>EN</th>
                                    <th>RU</th>
                                    <th></th>
                                  </tr>
                                </thead>
                                <tbody>

                                </tbody>
                              </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade bs-example-modal-lg dictionary_modal" tabicommon_selling_modalndex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                        <h4 class="modal-title" id="myLargeModalLabel">Soraqça</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row form-group hidden">
                          <div class="col col-md-3"><label for="dictionary_type_list" class=" form-control-label">Tipi</label></div>
                          <div class="col-12 col-md-9">
                            <select name="select" id="dictionary_type_list" class="form-control">
                              
                            </select>
                          </div>
                        </div>
                        <div class="row form-group hidden">
                          <div class="col col-md-3"><label for="dictionary_type_list" class=" form-control-label">Asılılıq</label></div>
                          <div class="col-12 col-md-9">
                            <select id="dictionary_list_by_type" class="form-control">
                              
                            </select>
                          </div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="product_name" class=" form-control-label">Az</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="name_az" name="text-input" placeholder="" class="form-control"></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="product_name" class=" form-control-label">En</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="name_en" name="text-input" placeholder="" class="form-control"></div>
                        </div>
                        <div class="row form-group">
                          <div class="col col-md-3"><label for="product_name" class=" form-control-label">Ru</label></div>
                          <div class="col-12 col-md-9"><input type="text" id="name_ru" name="text-input" placeholder="" class="form-control"></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        
                        <button type="button" class="btn btn-default" data-dismiss="modal">Bağla</button>
                        <button type="button" class="btn btn-primary btn-ndu-dictionary">Təsdiq et</button>

                    </div>
                </div>
            </div>
        </div> 
    </div><!-- /#right-panel -->
    <%@include file="../include/admin_footer.jsp" %>
    <script>
        $(function() {
            
            $('#main-menu a i[data-type="dictionary"]').parents('li').addClass('active');
            
            Product.Proxy.getDictionariesType(function(data) {
                if(data) {
                    var html ='';
                    $.each(data, function(i, v){
                        html += '<tr data-id="'+v.id+'">'+
                                    '<td>'+(++i)+'</td>'+
                                    '<td>'+v.value[Product.lang]+'</td>'+
                                '</tr>';
                    })
                    $('#dictionary_type tbody').html(html);
                    html='';
                    $.each(data, function(i, v){
                        html += '<option value="'+v.id+'">'+v.value[Product.lang]+'</option>'
                    })
                    $('#dictionary_type_list').html(html);
                }
            })
            
        })
        
    </script>
 

</body>
</html>
