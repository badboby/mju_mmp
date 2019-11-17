<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form id="deleteF" action="" method="post">
    <input type="hidden" name="_method" value="DELETE"/>
</form>
    <div id="myDelete" class="modal modal-primary" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">删除</h4>
                </div>
                <div class="modal-body">


                    <div class="box-body">
                        <div class="form-horizontal">



                            确定删除此项？



                        </div>
                    </div>



                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-outline" data-dismiss="modal">关闭</button>
                    <button type="button" name="deleteP" class="btn btn-outline" data-dismiss="modal">删除</button>
                </div>

            </div>
            <!-- /.modal-content -->
        </div>

        <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->

