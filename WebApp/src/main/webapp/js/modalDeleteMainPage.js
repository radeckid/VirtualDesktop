$('#deleteModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget); // Button that triggered the modal
    var name = button.data('name');

    document.getElementById('delete-name').innerHTML = name;
    document.getElementById('form-value').value = name;

    var form = document.getElementById('deleteForm');
    form.action = 'DeleteFileServlet'
});