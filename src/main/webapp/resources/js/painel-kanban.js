setTimeout(atualizaPainel, 10000);

function atualizaPainel() {
    const queryString = window.location.search;
    if (queryString == null)
        return;

    const urlParams = new URLSearchParams(queryString);
    const page = parseInt(urlParams.get("page"));
    if (page == null)
        return;

    const regex = /\?page=\d*/;
    window.location.replace(window.location.href.replace(regex, "?page=" + (page + 1)));
} 
