const token = "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJxdWFudHVhbmh1eSIsInN1YiI6IjIiLCJleHAiOjE3Mjk2MDA4MzgsImlhdCI6MTcyOTU2NDgzOCwianRpIjoiMGU1ZDc5ZjUtNjkxMi00MDlmLWI5ZjEtMTVmZTczMjVhZmJjIiwic2NvcGUiOiJBRE1JTiJ9.49JnySIMtn92J0D9HLLPK5YseSE_phutI74lxko5RTrfRI1Zux_30YVqfNPsRe907bceA3jxI7iQjGl6ePpZcQ";
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