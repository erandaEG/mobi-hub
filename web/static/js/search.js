$("#inpt_search").on('focus', function () {
    $(this).parent('label').addClass('active');
});

$("#inpt_search").on('blur', function () {
    if ($(this).val().length === 0)
        $(this).parent('label').removeClass('active');
});

$("#inpt_search").keydown(function (e) {
    // prevent: "e", "=", ",", "-", "."
    if ([69, 187, 188, 189, 190, 107, 109, 110].includes(e.keyCode)) {
        e.preventDefault();
    }
});

$(document).on('keypress', function (e) {
    if (e.which === 13) {
        $("#search_form").submit(function (ex) {
            ex.preventDefault(); // stop form submit and do your ajax call

            var searchData = $("#inpt_search").val();
            $.ajax({
                type: "POST",
                url: "servlet_home",
                data: {search: searchData},
                cache: false,
                success: function (response) {
                    if (response === "Connection Error!") {
                        alert("Oops! Seems like we faced an internal server error! Please try performing the search again.");
                    } else if (response === "Invalid Input!") {
                        alert("Oops! No smartphones available for the requested price!\n\n - You probably entered a rather low price for a smartphone\n\n\nHint: Try entering a value between 10,000 and 300,000");
                    } else if(response === "No Internet!"){
                        alert("Oops! Seems like there's no stable connection available! Please check your internet connection and try again.");
                    } else if(response === "No Smartphones!"){
                        alert("Oops! Seems like there are no smartphones available for the budget you provided. Please try performing the search again with a higher amount.");
                    } else {
                        location.href = "servlet_budgetResults";
                    }
                }
            });
        });
    }
});