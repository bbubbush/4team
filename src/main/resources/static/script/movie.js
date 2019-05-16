$(document).ready(()=>{
    let param = new Object();

    // 조회일자 유효성 검사
    let vaildateParam = ()=>{
        if ($.trim($('#targetDt').val()) == "") {
            alert('조회일자는 필수값입니다');
            return false;
        }

        if ($.trim($('#targetDt').val()).length != 8) {
            alert('조회일자의 입력값이 올바르지 않습니다');
            return false;
        }

        if (!$.isNumeric($.trim($('#targetDt').val()))) {
            alert('조회일자는 숫자만 입력할 수 있습니다');
            return false;
        }

        return true;
    } // End of vaildateParam function

    // 파라미터 세팅
    let makeParam = () => {
        let result = {};
        result.targetDt = $('#targetDt').val();

        // 확인할 영화 수
        if ($.trim($('#itemPerPage').val()) != "") {
            result.itemPerPage = $('#itemPerPage').val();
        }

        // 상업영화 / 다양성영화
        if ($.trim($('#multiMovieYn').val()) != "") {
            result.multiMovieYn = $('#multiMovieYn').val();
        }

        // 한국영화 / 외국영화
        if ($.trim($('#repNationCd').val()) != "") {
            result.repNationCd = $('#repNationCd').val();
        }
        return result;
    }// End of makeParam function


    $('#tbl_boxoffice').DataTable({
        language: {
            emptyTable: "조회된 결과가 없습니다.",
            processing: "처리중입니다.",
            loadingRecords: "잠시만 기다려 주세요.",
            paginate: {
                first: "첫 페이지",
                last: "마지막 페이지",
                next: "다음 페이지",
                previous: "이전 페이지"
            }
        },
        ajax: function (data, callback, settings) {
            if (!vaildateParam()) {
                return false;
            }

            let param = makeParam();

            console.log(param);

            movieApi.getDailyMovie(param, (response)=>{
                console.log(response);
                callback({
                    //recordsTotal: response.data.totalElements,
                    //recordsFiltered: response.data.totalElements,
                    data: response.data
                });
            })


        },


        columns: [
            {data: "rank"},
            {data: "showRange", render: function () {
                return $('#targetDt').val()
            }},
            {data: "movieNm"},
            {data: "audiAcc"},
            {data: "salesAmt"},
        ],
    });

    // 검색
    $('#submit').on('click', (event)=>{
        event.stopPropagation();
        console.log($('#tbl_boxoffice').DataTable().ajax);

        $('#tbl_boxoffice').DataTable().ajax.reload(null, false);
    })


});