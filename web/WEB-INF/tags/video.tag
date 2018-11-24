<%@ attribute name="id" required="true" type="java.lang.String" description="Id for video" %>
<%@ attribute name="description" required="true" type="java.lang.String" description="Description for video" %>
<%@ taglib prefix="inputTag" tagdir="/WEB-INF/tags" %>


<div class="col-3 col-xs-12 video-preview ">
    <div class="modal fade" tabindex="-1" id="${id}" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Body-->
                <div class="modal-body mb-0 p-0">
                    <div class="embed-responsive embed-responsive-16by9 z-depth-1-half">
                        <iframe class="embed-responsive-item"
                                src="https://www.youtube.com/embed/${id}"
                                allowfullscreen></iframe>
                    </div>
                </div>
                <!--Footer-->
                <div class="modal-footer justify-content-center">
                    <span class="mr-4">${description}</span>
                    <button type="button" class="btn btn-outline-primary btn-rounded btn-md ml-4"
                            data-dismiss="modal">Close
                    </button>
                </div>
            </div>
        </div>
    </div>

    <a><img class="img-fluid z-depth-1" src="https://img.youtube.com/vi/${id}/0.jpg"
            alt="video"
            data-toggle="modal" data-target="#${id}"></a>
</div>