import { getStatisticByRevenueAndDate, RevenueStatisticPerDate } from "../services/statistic-service";


// type CustomerStatisticPerDate = {
//     date: string,
//     count: number
// }

// type StatisticByCustomerAndDate = {
//     totalCustomer: number,
//     customerStatistics: CustomerStatisticPerDate[]
// }

export default async function Home() {


    const result = await getStatisticByRevenueAndDate("2024-10-20", "2024-10-24");

    return (
        <>
            <div>Tong doanh thu: {result.totalRevenue}</div>
            {result.revenueStatistics.map((statistic: RevenueStatisticPerDate) => (
                <div key={statistic.date}>
                    <p>{statistic.date}</p>
                    <p>{statistic.revenue}</p>
                </div>
            ))}
        </>
    );
}
