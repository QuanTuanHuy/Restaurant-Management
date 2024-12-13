const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJxdWFudHVhbmh1eSIsInN1YiI6IjEiLCJleHAiOjE3MzM0MTMzOTYsImlhdCI6MTczMzA1MzM5NiwianRpIjoiZGVlZWNhMDktNWE5Mi00NGQzLWFhMjQtYTgzMWYwOWE0YjNlIiwic2NvcGUiOiJBRE1JTiJ9.OspkfVYNAQSFKyQbjIuEh1roHY4OPRqW83CP3PHV8FHXaXehn803F4jnuMs6R1GIyCGluzAJse0NeY5Tj1hl9A";
const baseUrl = "http://127.0.0.1:8080/api/v1/statistics/"

export type RevenueStatisticPerDate = {
    date: string,
    revenue: number
}

export type StatisticByRevenueAndDate = {
    totalRevenue: number,
    revenueStatistics: RevenueStatisticPerDate[]
}


export async function getStatisticByRevenueAndDate(startDate: string, endDate: string): Promise<StatisticByRevenueAndDate> {
    const url = baseUrl + "by_revenue_and_date?" + "start_date=" + startDate + "&end_date=" + endDate;


    const data = await fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }
    });

    const statistics: StatisticByRevenueAndDate = await data.json().then(res => res.data);
    return statistics;

}