import React from 'react';
import { data } from "@/components/data";
import {
  Bar,
  BarChart,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
} from "recharts";
import { FaArrowLeft } from "react-icons/fa"; // Import back arrow icon
import { useNavigate } from "react-router-dom"; // Import useNavigate

// Custom tooltip component
const CustomTooltip = ({ active, payload }) => {
  if (active && payload && payload.length) {
    return (
      <div className="bg-gray-800 text-white border border-gray-600 p-2 rounded">
        <p className="font-bold">{`Operation: ${payload[0].payload.operation_type}`}</p>
        <p className="text-sm">{`MongoDB: ${payload[0].value}`}</p>
        <p className="text-sm">{`PostgreSQL: ${payload[1].value}`}</p>
        <p className="text-sm">{`Sharded MongoDB: ${payload[2].value}`}</p>
        <p className="text-sm">{`Cassandra: ${payload[3].value}`}</p>
      </div>
    );
  }

  return null;
};

export default function Overall() {
  const navigate = useNavigate(); // Initialize useNavigate

  let count = {
    "mongo": {
      "Create": { total: 0, count: 0 },
      "Read": { total: 0, count: 0 },
      "Update": { total: 0, count: 0 },
      "Delete": { total: 0, count: 0 }
    },
    "postgres": {
      "Create": { total: 0, count: 0 },
      "Read": { total: 0, count: 0 },
      "Update": { total: 0, count: 0 },
      "Delete": { total: 0, count: 0 }
    },
    "sharded_mongo": {
      "Create": { total: 0, count: 0 },
      "Read": { total: 0, count: 0 },
      "Update": { total: 0, count: 0 },
      "Delete": { total: 0, count: 0 }
    },
    "cassandra": {
      "Create": { total: 0, count: 0 },
      "Read": { total: 0, count: 0 },
      "Update": { total: 0, count: 0 },
      "Delete": { total: 0, count: 0 }
    }
  };

  data.forEach((item) => {
    const dbName = item.db.toLowerCase(); 
    const operationType = item.operation_type;

    if (count[dbName] && count[dbName][operationType]) {
      count[dbName][operationType]["total"] += item.time;
      count[dbName][operationType]["count"] += 1;
    }
  });

  let ChartData = [
    {
      "operation_type": "Create",
      "MongoDB": count["mongo"]["Create"]["count"] ? (count["mongo"]["Create"]["total"] / count["mongo"]["Create"]["count"]) : 0,
      "PostgreSQL": count["postgres"]["Create"]["count"] ? (count["postgres"]["Create"]["total"] / count["postgres"]["Create"]["count"]) : 0,
      "Sharded MongoDB": count["sharded_mongo"]["Create"]["count"] ? (count["sharded_mongo"]["Create"]["total"] / count["sharded_mongo"]["Create"]["count"]) : 0,
      "Cassandra": count["cassandra"]["Create"]["count"] ? (count["cassandra"]["Create"]["total"] / count["cassandra"]["Create"]["count"]) : 0,
    },
    {
      "operation_type": "Read",
      "MongoDB": count["mongo"]["Read"]["count"] ? (count["mongo"]["Read"]["total"] / count["mongo"]["Read"]["count"]) : 0,
      "PostgreSQL": count["postgres"]["Read"]["count"] ? (count["postgres"]["Read"]["total"] / count["postgres"]["Read"]["count"]) : 0,
      "Sharded MongoDB": count["sharded_mongo"]["Read"]["count"] ? (count["sharded_mongo"]["Read"]["total"] / count["sharded_mongo"]["Read"]["count"]) : 0,
      "Cassandra": count["cassandra"]["Read"]["count"] ? (count["cassandra"]["Read"]["total"] / count["cassandra"]["Read"]["count"]) : 0,
    },
    {
      "operation_type": "Update",
      "MongoDB": count["mongo"]["Update"]["count"] ? (count["mongo"]["Update"]["total"] / count["mongo"]["Update"]["count"]) : 0,
      "PostgreSQL": count["postgres"]["Update"]["count"] ? (count["postgres"]["Update"]["total"] / count["postgres"]["Update"]["count"]) : 0,
      "Sharded MongoDB": count["sharded_mongo"]["Update"]["count"] ? (count["sharded_mongo"]["Update"]["total"] / count["sharded_mongo"]["Update"]["count"]) : 0,
      "Cassandra": count["cassandra"]["Update"]["count"] ? (count["cassandra"]["Update"]["total"] / count["cassandra"]["Update"]["count"]) : 0,
    },
    {
      "operation_type": "Delete",
      "MongoDB": count["mongo"]["Delete"]["count"] ? (count["mongo"]["Delete"]["total"] / count["mongo"]["Delete"]["count"]) : 0,
      "PostgreSQL": count["postgres"]["Delete"]["count"] ? (count["postgres"]["Delete"]["total"] / count["postgres"]["Delete"]["count"]) : 0,
      "Sharded MongoDB": count["sharded_mongo"]["Delete"]["count"] ? (count["sharded_mongo"]["Delete"]["total"] / count["sharded_mongo"]["Delete"]["count"]) : 0,
      "Cassandra": count["cassandra"]["Delete"]["count"] ? (count["cassandra"]["Delete"]["total"] / count["cassandra"]["Delete"]["count"]) : 0,
    },
  ];

  return (
    <>
      {/* Back Button */}
      <button
        onClick={() => navigate("/")} // Navigate to home
        className="absolute top-4 left-4 bg-gray-700 text-white p-2 rounded-full hover:bg-gray-600 transition duration-300 flex items-center"
      >
        <FaArrowLeft />
      </button>

      <div className="flex flex-col items-center mt-10">
        <div className="text-xl font-bold mb-4">Overall Database Performance</div>

        <div className="flex justify-center h-[80vh] w-[75vw]">
          <BarChart data={ChartData} width={780} height={600}>
            <CartesianGrid vertical={false} />
            <XAxis dataKey="operation_type" />
            <YAxis />
            <Tooltip content={<CustomTooltip />} />
            <Legend />
            <Bar dataKey="MongoDB" name="MongoDB" fill="#f7f7f7" radius={4} />
            <Bar dataKey="PostgreSQL" name="PostgreSQL" fill="#2196F3" radius={4} />
            <Bar dataKey="Sharded MongoDB" name="Sharded MongoDB" fill="#0514eb" radius={4} />
            <Bar dataKey="Cassandra" name="Cassandra" fill="#8cb8ff" radius={4} />
          </BarChart>
        </div>
      </div>
    </>
  );
}
